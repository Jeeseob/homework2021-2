clear;

N0mat = [0.05:0.02:0.5];
cSNR = zeros(length(N0mat),1);
cBER = zeros(length(N0mat),1);

Nbit = 5000;
M=4;
%입력신호 만들기
inputBit = randi(2,1,Nbit) - 1;

for iN0 = 1: length(N0mat)
    N0 = N0mat(iN0);


% Parameter for Conv. coding
codeRate = 1/2;
Nstate = 4;

shiftRegister = [0 0 0];
gMatrix = [1 0 1; 1 1 1];
input_cc = [inputBit shiftRegister];
codeword = [];

for i = 1:length(input_cc);
    shiftRegister = [input_cc(i) shiftRegister(1:2)];
    
    for i=1:2
        xsum = 0;
        for k = 1:length(shiftRegister)
            xsum = xor(xsum, shiftRegister(k)*gMatrix(i,k));
        end
        codeword = [codeword xsum];
    end
end
Nc = 1/codeRate*(length(input_cc));



% Parameter Setting
Tsym = 1;
Nsym = Nc/log2(M);
Fs = 100;
Fc = 10;
% N0 = 0.2;

% Simulation
t = [Tsym/Fs : Tsym/Fs : Tsym*Nsym];
Tmax = length(t);

% Symbol 생성
symTable = zeros(1,4);
for i = 1:M
    i_m = 2*pi*(i-1)/M + pi/4;
    symTable(i) = cos(i_m) + j*sin(i_m);
end
bitTable = [0 0; 0 1; 1 0; 1 1];

% Basis Signal 생성
phi1 = cos(2*pi*Fc*t(1:Tsym*Fs));
Es = norm(phi1);
phi1 = phi1/Es;

phi2 = -sin(2*pi*Fc*t(1:Tsym*Fs));
Es = norm(phi2);
phi2 = phi2/Es;

%% TX

% 랜덤신호만들기
m = zeros(1,Nsym);
% 0, 1, 2, 3
for i= 1:Nsym
    m(i) = 0;
    for k = 1:2
        m(i) = 2*m(i) + codeword(2*(i-1)+k);
    end
end
% 0,1,2,3->1,2,3,4
m = m+1;

% 심볼신호 만들기
bbSym = symTable(m);

% Up-conversion (DAC 포함)
RFsignal = zeros(1,Tmax);
for iterT = 1:Tmax
    iterSym = floor((iterT-1)/Fs)+1;
    RFsignal(iterT) = real(bbSym(iterSym))*cos(2*pi*Fc*t(iterT))/Es - imag(bbSym(iterSym))*sin(2*pi*Fc*t(iterT))/Es;
end





%% RX
% Coherent Detection
Ich = RFsignal .* cos(2*pi*(Fc)*t)/Es;
Qch = RFsignal .* sin(2*pi*(Fc)*t)/Es;

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

% Optimal Receiver
hd_bbSym = zeros(1,Nsym);
hd_bit = [];
for i= 1:Nsym
    corr_result = bbSymN_rx(i)*conj(symTable);
    [dammyVal hd_index] = max(real(corr_result));
    hd_bbSym(i) = symTable(hd_index);
    hd_bit = [hd_bit bitTable(hd_index,:)];
end

% Trellis information
%transition
trellis_transition = zeros(Nstate,2);
trellis_transition(1,1) = 1;
trellis_transition(1,2) = 3;
trellis_transition(2,1) = 1;
trellis_transition(2,2) = 3;
trellis_transition(3,1) = 2;
trellis_transition(3,2) = 4;
trellis_transition(4,1) = 2;
trellis_transition(4,2) = 4;

% output bitstream
trellis_out = ones(Nstate,Nstate)*-1; %prev state->next state
trellis_out(1,trellis_transition(1,1)) = 0;
trellis_out(1,trellis_transition(1,2)) = 3;
trellis_out(2,trellis_transition(2,1)) = 3;
trellis_out(2,trellis_transition(2,2)) = 0;
trellis_out(3,trellis_transition(3,1)) = 1;
trellis_out(3,trellis_transition(3,2)) = 2;
trellis_out(4,trellis_transition(4,1)) = 2;
trellis_out(4,trellis_transition(4,2)) = 1;

% input bitstream
trellis_in = ones(Nstate,Nstate)*-1;
for i = 1:Nstate
    for k = 1:2 % k = 1,2 -> 0,1
        trellis_in(i,trellis_transition(i,k)) = k-1;
    end
end

% Viterbi Decoding
survivorPath = zeros(Nstate, Nc/2);
accum_metric = zeros(Nstate,1);
for i = 1:Nc/2
    if i ==1
        survivorNd = [1 0 0 0];
    else
        survivorNd = zeros(1,4);
        ndCand = transpose(find(survivorPath(:,i-1)>0));
        survivorNd(ndCand) = 1;
    end
    
    hopDist = ones(Nstate,Nstate)*-1;
    branchMetric = ones(Nstate,Nstate)*-1;
    
    for n1=1:Nstate
        for n2 = 1:Nstate
            if survivorNd(n1)>0 && length(find(trellis_transition(n1,:)==n2))>0
                path_out = [floor(trellis_out(n1,n2)/2) mod(trellis_out(n1,n2),2)];
                hopDist(n1,n2) = sum(abs(path_out-hd_bit((i-1)*2+1:i*2)));
                branchMetric(n1,n2) = accum_metric(n1) + hopDist(n1,n2);
                
            end
        end
    end
    
    %survivor path 선정
    for n = 1:Nstate
        pathIndex = find(branchMetric(:,n)>=0);
        if length(pathIndex)>0
            [accum_metric(n) minIndex] = min(branchMetric(pathIndex,n));
            survivorPath(n,i) = pathIndex(minIndex);
        end
    end

end
hd_uncode = zeros(1,Nc/2);
lastNd = 1;
for i = 1:Nc/2
    hd_uncode(Nc/2 - (i-1)) = trellis_in(survivorPath(lastNd,Nc/2-(i-1)),lastNd);
    lastNd = survivorPath(lastNd,Nc/2-(i-1));
end
hd_uncode = hd_uncode(1:Nbit);

BER = sum(abs(hd_uncode - inputBit)>0.01)/Nbit

SER = sum( abs(hd_bbSym - bbSym) > 0.01) /Nsym
cSNR(iN0) = SNR;
cBER(iN0) = BER;
end

plot(cSNR,cBER);