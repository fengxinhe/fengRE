f=imread('/Users/dingpeien/Desktop/学校活动/数字图像处理-李宏宇/testimage.bmp'); 
figure
imshow(f);
E=4;
k0=0.4;
k1=0.02;
k2=0.4;
f=histeq(f,32);
[m,n]=size(f);
K=m;
Mg=0;
for k=0:255 
     GP(k+1)=length(find(I==k))/(m*n);  %accelerate the probability
end
for i=1:256
    Mg=Mg+sum(find(GP==i))*i;%计算Mg
end

D2g=0;
for j=1:256
    D2g=D2g+(k-Mg)^2*sum(find(GP==i));
end
Dg=sqrt(D2g);
for i=2:m-1
    for j=2:n-1
        MSxy=f(i-1,j-1)*sum(find(GP==f(i-1,j-1)))
        +f(i-1,j)*sum(find(GP==f(i-1,j)))
        +f(i-1,j+1)*sum(find(GP==f(i-1,j+1)))
        +f(i,j-1)*sum(find(GP==f(i,j-1)))
        +f(i,j)*sum(find(GP==f(i,j)))
        +f(i,j+1)*sum(find(GP==f(i,j+1)))
        +f(i+1,j-1)*sum(find(GP==f(i+1,j-1)))
        +f(i+1,j)*sum(find(GP==f(i+1,j)))
        +f(i+1,j+1)*sum(find(GP==f(i+1,j+1)));
        theta2xy=(f(i-1,j-1)-MSxy)*sum(find(GP==f(i-1,j-1)))
        +(f(i-1,j)-MSxy)*sum(find(GP==f(i-1,j)))
        +(f(i-1,j+1)-MSxy)*sum(find(GP==f(i-1,j+1)))
        +(f(i,j-1)-MSxy)*sum(find(GP==f(i,j-1)))
        +(f(i,j)-MSxy)*sum(find(GP==f(i,j)))
        +(f(i,j+1)-MSxy)*sum(find(GP==f(i,j+1)))
        +(f(i+1,j-1)-MSxy)*sum(find(GP==f(i+1,j-1)))
        +(f(i+1,j)-MSxy)*sum(find(GP==f(i+1,j)))
        +(f(i+1,j+1)-MSxy)*sum(find(GP==f(i+1,j+1)));   
        theta=sqrt(double(theta2xy));
    if MSxy<=k0*Mg && k2*Dg<=theta&&theta<=k2*Dg
        f(i,j)=E*f(i,j);
    end
    end
end
figure
imshow(f);






    