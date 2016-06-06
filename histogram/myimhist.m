function [ output_args ] = myimhist( I )
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
[m,n]=size(I);
for k=0:255 
     GP(k+1)=length(find(I==k))/(m*n);  %accelerate the probability
end
bar(1:256,GP); 
title('histogram')

end

