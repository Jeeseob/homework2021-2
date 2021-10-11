clear;

% Parameter Setting

Tsym = 1; % symbol duration
Nsym = 1000; % number of symbol
Fs = 100; % sampling size
Fc = 10; % carrier frequency
Fe = 0; % 나중을 위해서  일단 남겨둔다.

N0 = 0.5;

% Simulation
t = [Tsym/Fs : Tsym/Fs : Tsym*Nsym];
Tmax = length(t);

% Symbol 생성
M=4;
symTable = zeros(1,16);

for i = 1:length(symTable)
    if mod(i,M) == 0
        symTable(i) = (2*fix(i/M)-1-M)*j+3;
    else
        symTable(i) = (2*(fix(i/M)+1)-1-M)*j+2*mod(i,M)-1-M;
    end
end
% Basis Signal 생성
phi1 = cos(2*pi*Fc*t(1:Tsym*Fs));
Es = norm(phi1);
phi1 = phi1/Es;

phi2 = -sin(2*pi*Fc*t(1:Tsym*Fs));
Es = norm(phi2);
phi2 = phi2/Es;

%% TX

% 랜덤신호만들기
m = randi(16,1,Nsym); %1~M 중 Nsym 갯수 만큼 랜덤

% 심볼신호 만들기
bbSym = zeros(1,Nsym);

for i = 1:length(m)
    if mod(m(i),M) == 0
        bbSym(i) = (2*fix(m(i)/M)-1-M)*j+3;
    else
        bbSym(i) = (2*(fix(m(i)/M)+1)-1-M)*j+2*mod(m(i),M)-1-M;
    end
end
% Up-conversion (DAC 포함)
RFsignal = zeros(1,Tmax);
for iterT = 1:Tmax
    iterSym = floor((iterT-1)/Fs)+1;
    RFsignal(iterT) = real(bbSym(iterSym))*cos(2*pi*Fc*t(iterT))/Es - imag(bbSym(iterSym))*sin(2*pi*Fc*t(iterT))/Es;
end

% 참고1 - signal 보여주기
% figure(1)
% plot(t,RFsignal);
% xlim([0 2]);

% 참고2 - 심볼신호 보여주기
s = zeros(2,Nsym);
for i = 1:Nsym
    intStart = 1+(i-1)*Tsym*Fs;
    intEnd = i*Tsym*Fs;
    s(1,i) = sum(RFsignal(intStart:intEnd).*phi1);
    s(2,i) = sum(RFsignal(intStart:intEnd).*phi2);
end
figure(2)
scatter(s(1,:),s(2,:),'r*');
grid on;
axis([-4 4 -4 4]);


%% RX
% Coherent Detection
Ich = RFsignal .* cos(2*pi*(Fc+Fe)*t)/Es;
Qch = RFsignal .* sin(2*pi*(Fc+Fe)*t)/Es;

% Baseband Signal Representation
for i = 1:Nsym
    n_start = (i-1)*Tsym*Fs;
    bbSym_rx(i) = sum(Ich(n_start+1:n_start+Tsym*Fs) - j*Qch(n_start+1:n_start+Tsym*Fs));
end
sigPower = mean(abs(bbSym_rx).^2);

% Noise Insertion
noise = sqrt(N0)*randn(1,length(bbSym_rx)) + j*sqrt(N0)*randn(1,length(bbSym_rx));
bbSymN_rx = bbSym_rx+noise;
noisePower = mean(abs(noise).^2);
SNR = 10*log10(sigPower/noisePower)

% Signal Space Representation
figure(3)
scatter(real(bbSymN_rx), imag(bbSymN_rx));
grid on;
axis([-4 4 -4 4]);
hold on;
scatter(s(1,:),s(2,:),'r*');

% Optimal Receiver
hd_bbSym = zeros(1,Nsym);
for i= 1:Nsym
    % 각 값과의 거리를 이용하여 가장 가까운 위치의 값으로 추측
    corr_result = (real(bbSym_rx(i))-real(symTable)).^2 + (imag(bbSymN_rx(i))-imag(symTable)).^2;
    % 거리로 계산 하기 때문에 가장 짧은 거리를 가진 값의 index를 활용한다.
    [dammyVal hd_index] = min(corr_result);
    hd_bbSym(i) = symTable(hd_index);
end
SER = sum(abs(hd_bbSym - bbSym) > 0.01) /Nsym

