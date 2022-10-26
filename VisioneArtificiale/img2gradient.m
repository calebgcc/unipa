function [output, palette]=img2gradient(img, show)

    kernel = [-1 -2 -1; 0 0 0; 1 2 1]; % sobel operator
    Dx = conv_generic(img, kernel);
    Dy = conv_generic(img, kernel');
    magnitude = sqrt(Dx.^2 + Dy.^2); % magnitude of the gradient
    
    % set the magnitude between 0 and 1
    magnitude = rescale(magnitude);

    % binarization of magnitude
    bin = imbinarize(magnitude);

    % angle of the gradient
    alpha = atan2(Dy, Dx);
    alpha = ((alpha + pi)/(2*pi))*359; % conversion from [-pi, pi] to [0, 359]
    alpha = alpha + 1; % so that min is 1 and not 0 (max is now 360)

    palette = hsv(360);
    palette = [0 0 0; palette];

    output = alpha.*bin;
    if show==true
        figure; imshow(alpha.*bin, palette)
    end
