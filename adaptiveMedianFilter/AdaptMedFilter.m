I=imread('/Users/dingpeien/Desktop/学校活动/数字图像处理-李宏宇/imgWithSaltPepperNoise.bmp'); 
Inoise=I;

[m,n]=size(Inoise);
nmin=3;
nmax=9;

Imf=Inoise;

I_ex=[zeros((nmax-1)/2,n+(nmax-1));
    zeros(m,(nmax-1)/2),Inoise,zeros(m,(nmax-1)/2);zeros((nmax-1)/2,n+(nmax-1))];

for x=1:m
    for y=1:n
        for in=nmin:2:nmax
            Sxy=I_ex(x+(nmax-1)/2-(in-1)/2:x+(nmax-1)/2+(in-1)/2,y+(nmax-1)/2-(in-1)/2:y+(nmax-1)/2+(in-1)/2);
            Smax=max(max(Sxy));
            Smin=min(min(Sxy));
            Smed=median(median(Sxy));
            
            if Smed>Smin&&Smed<Smax
                if Imf(x,y)<=Smin||Imf(x,y)>=Smax
                    Imf(x,y)=Smed;
                end
                break
            end
        end
        Imf(x,y)=Smed;
    end
end
Imf1=medfilt2(Inoise,[3,3]);
Imf2=medfilt2(Imf1,[3,3]);
figure
%subplot(221)
imshow(Inoise);xlabel('原噪声图像');
%subplot(222)
figure
imshow(Imf);xlabel('自适应中值滤波');
%subplot(223)
figure
imshow(Imf2);xlabel('普通中值滤波');
       
        
        
        
        
        
        
        
        
        