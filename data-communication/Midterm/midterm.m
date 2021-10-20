clear;

% Parameter Setting
Tsym = 1;
Nsym = 4;
Fs = 100;
Fc = 10;
Fe = 0;
N0 = 0;

% Simulation
t = [Tsym/Fs : Tsym/Fs : Tsym*Nsym];
Tmax = length(t);

% Symbol 생성
M=16;
symTable = zeros(1,16);
for i = 1:M
    if floor((i-1)/4) == 0
        a=-1;
        b=-1;
    elseif floor((i-1)/4) == 1
        a=1;
        b=-1;
        
    elseif floor((i-1)/4) == 2
        a=-1;
        b=1;
    else
        a=1;
        b=1;
    end
    
    if mod(i-1,4) == 0
        realSym = a*3;
        imagSym = b*1;
    elseif mod(i-1,4) ==1
        realSym = a*1;
        imagSym = b*1;
    elseif mod(i-1,4) == 2
        realSym = a*3;
        imagSym = b*3;
    else
        realSym = a*1;
        imagSym = b*3;
    end
    
    symTable(i) = realSym+j*imagSym;
%     i_m = 2*pi*(i-1)/M + pi/4;
%     symTable(i) = cos(i_m) + j*sin(i_m);
end

% Basis Signal 생성
phi1 = cos(2*pi*Fc*t(1:Tsym*Fs));
Es = norm(phi1);
phi1 = phi1/Es;

phi2 = -sin(2*pi*Fc*t(1:Tsym*Fs));
Es = norm(phi2);
phi2 = phi2/Es;

%% TX

m = randi(M,1,Nsym);

m = [6,9,15,7];

% 심볼신호 만들기
for i = 1:length(m)
    if floor((m(i)-1)/4) == 0
        a=-1;
        b=-1;
    elseif floor((m(i)-1)/4) == 1
        a=1;
        b=-1;    
    elseif floor((m(i)-1)/4) == 2
        a=-1;
        b=1;
    else
        a=1;
        b=1;
    end

    if mod(m(i)-1,4) == 0
        realSymR = a*3;
        imagSymR = b*1;
    elseif mod(m(i)-1,4) ==1
        realSymR = a*1;
        imagSymR = b*1;
    elseif mod(m(i)-1,4) == 2
       realSymR = a*3;
       imagSymR = b*3;
    else
        realSymR = a*1;
        imagSymR = b*3;
    end

    bbSym(i) = realSymR+j*imagSymR;
end

% Up-conversion (DAC 포함)
RFsignal = zeros(1,Tmax);
for iterT = 1:Tmax
    iterSym = floor((iterT-1)/Fs)+1;
    realSymbol(iterT) = real(bbSym(iterSym));
    imageSymbol(iterT)= imag(bbSym(iterSym));
    
    RFsignal(iterT) = real(bbSym(iterSym))*cos(2*pi*Fc*t(iterT))/Es - imag(bbSym(iterSym))*sin(2*pi*Fc*t(iterT))/Es;
end

% 실수부 (I-Channel)
figure(2)
plot(t,realSymbol);
axis([0 4 -4 4]);

% 허수부 (Q-Channel)
figure(3)
plot(t,imageSymbol);
axis([0 4 -4 4]);


%참고1 - signal 보여주기
figure(4)
plot(t,RFsignal);
xlim([0 4]);

% 참고2 - 심볼신호 보여주기
s = zeros(2,Nsym);
for i = 1:Nsym
    intStart = 1+(i-1)*Tsym*Fs;
    intEnd = i*Tsym*Fs;
    s(1,i) = sum(RFsignal(intStart:intEnd).*phi1);
    s(2,i) = sum(RFsignal(intStart:intEnd).*phi2);
end
figure(1)
scatter(s(1,:),s(2,:),'r*');
grid on;
axis([-4 4 -4 4]);