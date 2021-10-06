symbol1 = -1+j;
symbol2 = -3+3j;
symbol3 = 1-j;
Ts = 1;
fs = 30;
t = [1/fs : 1/fs : Ts];

y1 = real(symbol1)*cos(2*pi/Ts*t) - imag(symbol1)*sin(2*pi/Ts*t);
y2 = real(symbol2)*cos(2*pi/Ts*t) - imag(symbol2)*sin(2*pi/Ts*t);
y3 = real(symbol3)*cos(2*pi/Ts*t) - imag(symbol3)*sin(2*pi/Ts*t);

figure(2)

plot(t, y1, 'bo');
hold on;
plot(t, y2, 'rx');
hold on;
plot(t, y3, 'g*');

title('30Hz sampling - homework');
xlabel('time');
ylabel('value');