clear;

% Parameter Setting
Tsym = 0.125;
Nsym = 16;
fs = 2000;
fc = 100;

N0 = 0.00001;
alpha=0.5;

% Simulation
t = [1/fs : 1/fs : Tsym*Nsym];
Tmax = length(t);

% channel delay:tau
tau = 0.001;

% OFDM parameters
Nfft = 8;
p_index = [1:Nfft];

% Symbol 생성
M=4;
symTable = zeros(1,4);
for i = 1:M
    i_m = 2*pi*(i-1)/M + pi/4;
    symTable(i) = cos(i_m) + j*sin(i_m);
end

% Basis Signal 생성
phi1 = cos(2*pi*fc*t(1:Tsym*fs));
Es = norm(phi1);
phi1 = phi1/Es;

phi2 = -sin(2*pi*fc*t(1:Tsym*fs));
Es = norm(phi2);
phi2 = phi2/Es;

%% TX

% 랜덤신호만들기
m = [1,2,3,4,1,2,2,3,3,3,4,4,4,4,1,2];

% 심볼신호 만들기
theta_m = 2*pi*(m-1)/M + pi/4;
bbSym = cos(theta_m) + j*sin(theta_m);
bbSym_orig = bbSym;


% OFDM modulation
% if Nfft>0
%     bbFsym = bbSym;
%     bbSym = [];
%     for i = 1:Nsym/Nfft
%         input = bbFsym(Nfft*(i-1)+1:Nfft*i);
%         bbSym = [bbSym ifft(input,Nfft)];
%     end
% end


% DAC
bbInput = zeros(1,Tmax);
for i = 1:Nsym
    bbInput(Tsym*fs*(i-1)+1) = bbSym(i);
end

reconstFilter = rcosdesign(alpha, 5, Tsym*fs/5);
reconstNorm = sqrt(sum(reconstFilter.^2)/Tsym/fs);
bbsignal = conv(bbInput, reconstFilter);
bbsignal = [bbsignal(1:Tmax)];

% up-conversion
RFsignal = real(bbsignal).*cos(2*pi*fc*(t-tau))/Es/reconstNorm - imag(bbsignal).*sin(2*pi*fc*(t-tau))/Es/reconstNorm; 


%% RX
% Coherent Detection
Ich = RFsignal .* cos(2*pi*(fc)*t)/Es;
Qch = RFsignal .* sin(2*pi*(fc)*t)/Es;

% matched filter
Ich_m = conv(Ich,reconstFilter);
Qch_m = conv(Qch,reconstFilter);

figure(1)
subplot(2,2,1)
plot(abs(bbsignal));
subplot(2,2,2)
plot(angle(bbsignal));
subplot(2,2,3)
plot(abs(Ich_m+j*Qch_m));
subplot(2,2,4)
plot(angle(Ich_m+j*Qch_m));

% Baseband Signal Representation
for i = 1:Nsym
    n_index = i*Tsym*fs;
    bbSym_rx(i) = Ich_m(n_index) - Qch_m(n_index)*j;
end

% Noise Insertion
noise = sqrt(N0)*randn(1,length(bbSym_rx)) + j*sqrt(N0)*randn(1,length(bbSym_rx));
bbSym_rx = bbSym_rx+noise;

SNR = 10*log10(mean(abs(bbSym_rx).^2)/mean(abs(noise).^2))

% OFDM demodulation
if Nfft>0
    bbSymT_rx = bbSym_rx;
    bbSym_rx = [];
    for i = 1:Nsym/Nfft
        input = bbSymT_rx(Nfft*(i-1)+1:Nfft*i);
        bbSym_rx = [bbSym_rx fft(input,Nfft)];
    end
end

% figure(1);
% scatter(real(bbSym_rx),imag(bbSym_rx));

% one-tap equalization
for i = 1:Nsym/Nfft
    if i==1
        OFDMsym = bbSym_rx(1:8);
        h(p_index) = conj(bbSym_orig(p_index)).*OFDMsym(p_index);
        phase_diff = angle(h);
    end
    bbSym_rx(Nfft*(i-1)+1:Nfft*i) = bbSym_rx(Nfft*(i-1)+1:Nfft*i).*exp(-j*phase_diff);
end

% figure(1);
% hold on;
% scatter(real(bbSym_rx),imag(bbSym_rx),'r');
% hold off;

% Optimal Receiver
hd_bbSym = zeros(1,Nsym);
for i= 1:Nsym
    corr_result = bbSym_rx(i)*conj(symTable);
    [dammyVal hd_index] = max(real(corr_result));
    hd_bbSym(i) = symTable(hd_index);
end

SER = sum( abs(hd_bbSym - bbSym_orig) > 0.01) /Nsym