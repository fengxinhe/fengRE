I=imread('/Users/dingpeien/Desktop/ѧУ�/����ͼ����-�����/imgWithSaltPepperNoise.bmp'); 
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
imshow(Inoise);xlabel('ԭ����ͼ��');
%subplot(222)
figure
imshow(Imf);xlabel('����Ӧ��ֵ�˲�');
%subplot(223)
figure
imshow(Imf2);xlabel('��ͨ��ֵ�˲�');
       
        
        
        
        
        
        
        
        
        