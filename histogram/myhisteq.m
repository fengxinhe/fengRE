function X = myhisteq( I )
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here

[m,n]=size(I);
for k=0:255 
     GP(k+1)=length(find(I==k))/(m*n);  %accelerate the probability
end
EQ=zeros(1,256); 
for i=1:256
    for j=1:i 
          EQ(i)=GP(j)+EQ(i);
    end 
end 
EQ1=round((EQ*256)+0.5); %��Sk�鵽������ĻҶ� 
for i=1:256 
    GPeq(i)=sum(GP(find(EQ1==i)));%��������ÿ���Ҷȼ����ֵĸ��� 
end 
X=I;
for i=0:255
    X(find(I==i)) = EQ1(i+1);
end

end

