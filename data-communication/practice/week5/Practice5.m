clear;

% parameter  setting
 
Tsym = 1; % symbol duration
Nsym = 1000; % number of symbol
Fs = 100; % sampling size
Fc = 10; % carrier frequency
Fe = 0; % 나중을 위해서  일단 남겨둔다.

% Noise level
N0 = 0.1; % noise 


% simulation

t = [Tsym/Fs : Tsym/Fs : Tsym*Nsym];
Tmax = length(t);

%Symbol 생성

M = 4; % QAM이라는 의미
SymTable = zero(1,4);

for i = 1:M
    i_m = 2*pi*(i-1)/M+pi/4; 
    
    symTable(1,i) = cos(i_m) + j*sin(i_m);

end

%Basis Signal 생성

phi1 = cos(2*pi*Fc*t(1:Tsym*Fs));
Es = norm(phi1);
phi1 = phi1/Es;

phi2 = -sin(2*pi*Fc*t(1:Tsym*Fs));
Es = norm(phi2);
phi2 = phi2/Es;



%% TX




