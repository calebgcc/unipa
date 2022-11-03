function [q,d]=dithering(img, N)
    img = single(img);
    map = round(0:255/(N-1):255);
    map = map(floor((0:255)/(256/N))+1);
    q = map(img + 1);
    d = map(img + 1);

    [H, W] = size(img);
    
    for h=1:H
        for w=1:W
            e = img(h,w)-d(h,w);
            if w < W 
                d(h,w+1) = d(h,w+1) + (e * 7/16);
            end
    
            if h < H
                d(h+1,w) = d(h+1,w) + (e * 5/16);
            end
    
            if h<H && w>1
                d(h+1,w-1) = d(h+1,w-1) + (e * 3/16);
            end
    
            if h<H && w<W
                d(h+1,w+1) = d(h+1,w+1) + (e * 1/16);
            end
        end
    
    end
