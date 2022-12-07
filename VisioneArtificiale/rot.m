function output=rot(img, alpha)
    alpha = (2*pi*alpha)/360;
    [h,w] = size(img);
    
    % A.(1,1)       B.(1,w)
    %
    %
    % C.(h,1)       D.(h,w)


    xp = [0 0 0 0];
    xp(1) = round(cos(alpha) - sin(alpha));
    xp(2) = round(cos(alpha) - w*sin(alpha));
    xp(3) = round(h*cos(alpha) - sin(alpha));
    xp(4) = round(h*cos(alpha) - w*sin(alpha));
    H = max(xp) - min(xp);

    yp = [0 0 0 0];
    yp(1) = round(sin(alpha) + cos(alpha));
    yp(2) = round(sin(alpha) + w*cos(alpha));
    yp(3) = round(h*sin(alpha) + cos(alpha));
    yp(4) = round(h*sin(alpha) + w*cos(alpha));
    W = max(yp) - min(yp);

    output = zeros(H,W, 'uint8');

    Hm = round(H/2);
    Wm = round(W/2);

    hm = round(h/2);
    wm = round(w/2);

    for i=(-Hm:Hm)
        for j=(-Wm:Wm)
            x = round(i*cos(alpha) + j*sin(alpha)) + hm + 1;
            y = round(j*cos(alpha) - i*sin(alpha)) + wm + 1;

            if x>0 && x<=h && y>0 && y<=w
                output(i+Hm+1, j+Wm+1) = img(x,y);
            end

        end
    end

end