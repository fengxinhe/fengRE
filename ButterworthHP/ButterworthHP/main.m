%This script shows how to perform Butterworth high-pass filtering in the Fourier domain.
%by Hongyu Li, SSE, Tongji University, Shanghai, China, Oct. 2014
im = im2single(imread('sse.bmp'));
[rows, cols] = size(im);

%pad the image to its double size by populating zeros
paddedIm = padarray(im, [rows, cols],'post');
imfft = fft2(paddedIm);
imfftShifted = fftshift(imfft);

%construct a low-pass filter (of a Gaussian shape) in the Fourier domain
D0 = 50;
n = 1;
HPFilterKernel = 1 - ButterworthFilter(D0, 2*rows, 2*cols,n);
modifiedFourierTransform = ifftshift(imfftShifted .* HPFilterKernel);

%go back to the spatial domain
resultInSpatialDomain = real(ifft2(modifiedFourierTransform));

%extract the final processed result 
finalResult = resultInSpatialDomain(1:rows, 1:cols);
figure;
imshow(finalResult,[0,1]);
