%% 1st problem

result1 = calSum(10)

%% 2nd Problem

result2 = chkPrime(10)
result3 = chkPrime(13)

%% 3rd Problem

fs = 100;
Tmax = 2;
t = [ 1/fs : 1/fs : Tmax]; % 1X200
y = cos(2*pi*t); % 1X200
z = sin(2*pi*t); % 1X200

figure(1)

% plot(t, y,'bo', t, z, 'rx');
plot(t, y, 'bx');
hold on;
plot(t, z, 'go');

title('100Hz sampling');
xlabel('time');
ylabel('value');

%% 4th Problem

symbol = 3+3j;
Ts = 1;
fs = 10;
t = [1/fs : 1/fs : Ts];

y = real(symbol)*cos(2*pi/Ts*t) - imag(symbol)*sin(2*pi/Ts*t);
figure(2)

plot(t, y, 'o');

title('10Hz sampling');
xlabel('time');
ylabel('value');

