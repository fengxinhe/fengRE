function Huv = ButterworthFilter(D0,rows,cols,n)

[X,Y] = meshgrid(1:cols, 1:rows);

Duv = sqrt((X - cols/2).^2 + (Y - rows/2).^2);
Huv = 1 ./ (1 + (Duv/D0).^(2*n));