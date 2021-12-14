function output = viterbiDec(input)

input = [0     0     0     0     1     1     1     1     0     0     0     0     0     0     0     0];



%Convolutional code information
%transition (prev state, inputBit :: output_state)
codeRate = 1/2;
padding = 3;
Nstate = 2^(1/codeRate);

trellis_transition = zeros(Nstate,2);
trellis_transition(1,1) = 1;
trellis_transition(1,2) = 4;
trellis_transition(2,1) = 2;
trellis_transition(2,2) = 4;
trellis_transition(3,1) = 1;
trellis_transition(3,2) = 4;
trellis_transition(4,1) = 2;
trellis_transition(4,2) = 3;

%transition (prev state, inputBit :: output)
trellis_out = ones(Nstate,Nstate)*-1;
trellis_out(1,trellis_transition(1,1)) = 0;
trellis_out(1,trellis_transition(1,2)) = 2;
trellis_out(2,trellis_transition(2,1)) = 0;
trellis_out(2,trellis_transition(2,2)) = 2;
trellis_out(3,trellis_transition(3,1)) = 1;
trellis_out(3,trellis_transition(3,2)) = 3;
trellis_out(4,trellis_transition(4,1)) = 1;
trellis_out(4,trellis_transition(4,2)) = 3;

trellis_in = ones(Nstate,Nstate)*-1;
for i = 1 : Nstate
    for k = 1 : 2
        trellis_in(i,trellis_transition(i,k)) = k-1;
    end
end

Nc = length(input);

%channel decoding : Viterbi decoding
survivorPath = zeros(Nstate,Nc/2);
accum_metric = zeros(1,Nstate);
for i = 1 : Nc/2

    if i == 1
        survivorNd = [1 0 0 0];
    else
        survivorNd = zeros(1,4);
        ndCand = transpose(find(survivorPath(:,i-1) > 0));

        survivorNd(ndCand) = ones(1,length(ndCand));
    end

    branchMetric = ones(Nstate,Nstate)*-1; %src/dest nd
    hopDist = ones(Nstate,Nstate)*-1; %src/dest nd

    %path metric recalculation
    for n1 = 1 : Nstate %source nd
        for n2 = 1 : Nstate %dest nd
            if survivorNd(n1) > 0 && length(find(trellis_transition(n1,:) == n2)) > 0
                path_out = [floor(trellis_out(n1,n2)/2) mod(trellis_out(n1,n2),2)];
                hopDist(n1,n2) = sum(abs(path_out - input((i-1)*2+1:i*2)));
                branchMetric(n1,n2) = accum_metric(n1) + hopDist(n1,n2);
            end
        end
    end

    for n = 1 : Nstate
        pathIndex = find(branchMetric(:,n) >= 0);
        if length(pathIndex) > 0
            [accum_metric(n) minIndex] = min(branchMetric(pathIndex,n));

            survivorPath(n,i) = pathIndex(minIndex);
        end
    end
end

output = zeros(1,Nc/2);
lastNd = 1;
for i = 1 : Nc/2
    output(Nc/2-(i-1)) = trellis_in(survivorPath(lastNd,Nc/2-(i-1)),lastNd);
    lastNd = survivorPath(lastNd,Nc/2-(i-1));
end
output = output(1:(Nc/2-padding));














%channel decoding : Viterbi decoding
survivorPath = zeros(Nstate,Nc/2);
accum_metric = zeros(1,Nstate);
for i = 1 : Nc/2

    if i == 1
        survivorNd = [1 0 0 0];
    else
        survivorNd = zeros(1,4);
        ndCand = transpose(find(survivorPath(:,i-1) > 0));

        survivorNd(ndCand) = ones(1,length(ndCand));
    end

    branchMetric = ones(Nstate,Nstate)*-1; %src/dest nd
    hopDist = ones(Nstate,Nstate)*-1; %src/dest nd

    %path metric recalculation
    for n1 = 1 : Nstate %source nd
        for n2 = 1 : Nstate %dest nd
            if survivorNd(n1) > 0 && length(find(trellis_transition(n1,:) == n2)) > 0
                path_out = [floor(trellis_out(n1,n2)/2) mod(trellis_out(n1,n2),2)];
                hopDist(n1,n2) = sum(abs(path_out - input((i-1)*2+1:i*2)));
                branchMetric(n1,n2) = accum_metric(n1) + hopDist(n1,n2);
            end
        end
    end

    for n = 1 : Nstate
        pathIndex = find(branchMetric(:,n) >= 0);
        if length(pathIndex) > 0
            [accum_metric(n) minIndex] = min(branchMetric(pathIndex,n));

            survivorPath(n,i) = pathIndex(minIndex);
        end
    end
end

output = zeros(1,Nc/2);
lastNd = 1;
for i = 1 : Nc/2
    output(Nc/2-(i-1)) = trellis_in(survivorPath(lastNd,Nc/2-(i-1)),lastNd);
    lastNd = survivorPath(lastNd,Nc/2-(i-1));
end
output = output(1:(Nc/2-padding));