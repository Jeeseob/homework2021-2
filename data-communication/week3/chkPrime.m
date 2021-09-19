function result = chkPrime(N)

result = 1;
for i = 2:N-1
    if mod(N,i) == 0
        result = 0;
        break;
    end
end
