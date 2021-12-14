function output = ccEnc(input)

input = [0 0 1 1 0];



%Convolutional code information
%transition (prev state, inputBit :: output_state)
codeRate = 1/2;
shiftRegister = [0 0 0];
gMatrix = [1 1 0;1 0 1];
input_cc = [input shiftRegister];
output = [];



for i = 1 : length(input_cc);
    %shift operation
    shiftRegister = [input_cc(i) shiftRegister(1:2)];

    for i = 1 : 2
        if(i == 1)
            xsum = 0;
            xsum = xor(shiftRegister(1)*gMatrix(i,1),shiftRegister(2)*gMatrix(i,2));
            output = [output xsum];
        
        else
            xsum = 0;
            xsum = xor(shiftRegister(1)*gMatrix(i,1),shiftRegister(3)*gMatrix(i,3));
            output = [output xsum];
        end
    end
end


% for i = 1 : length(input_cc);
%     %shift operation
%     shiftRegister = [input_cc(i) shiftRegister(1:2)];
% 
%     for i = 1 : 2
%         xsum = 0;
%         for k = 1 : length(shiftRegister)
%             xsum = xor(xsum,shiftRegister(k)*gMatrix(i,k));
%         end
%         output = [output xsum];
%     end
% end