function ham6(img, path)

    global a b buffer buffersize fid; %#ok<GVMIS> 
    fid = fopen(path, 'w');
    a = 1;
    b = 8;
    buffersize = 2^10;
    buffer = zeros(1, buffersize, 'uint8');

    img4096 = uint8(img/17); % img with 16*16*16 colors
    [output, cmap] = rgb2ind(img4096*17,16);
    cmap = round((cmap*255)/17); % each color is now between 0 and 15
    
    for i=1:16
        writer(cmap(i,1),4);
        writer(cmap(i,2),4);
        writer(cmap(i,3),4);
    end
    
    cmap = single(cmap);
    img4096 = single(img4096);

    start = 2;
    last = cmap(output(1,1)+1, :); % last rgb value
    writer(0,2);
    writer(output(1,1), 4);
    for h=1:size(img4096, 1)
        for w=start:size(img4096,2)
            
            real_color = reshape(img4096(h,w,:),1,[]);

            % norma minima
            dm = sqrt(sum((real_color - cmap(output(h,w)+1,:)).^2));

            % change the red
            d1 = sqrt(sum(([real_color(2) real_color(3)] - [last(2) last(3)]).^2));

            % change green
            d2 = sqrt(sum(([real_color(1) real_color(3)] - [last(1) last(3)]).^2));
        
            % change blue
            d3 = sqrt(sum(([real_color(1) real_color(2)] - [last(1) last(2)]).^2));

            vet = [dm, d1, d2, d3];
            [~,i] = min(vet);
        
        
            writer(i-1, 2);
            switch i
                case 1
                    writer(output(h,w), 4);
                    last = cmap(output(h,w)+1, :);
                case 2
                    writer(real_color(1),4);
                    last(1) = real_color(1);
                case 3
                     writer(real_color(2), 4);
                     last(2) = real_color(2);
                case 4
                    writer(real_color(3), 4);
                    last(3) = real_color(3);
            end
        end
        start=1;
    end

    if ~(a==1 && b==8)
        if b==8
            a = a-1;
        end
        fwrite(fid,buffer(1:a), 'uint8');
    end

    fclose(fid);
end


function writer(value, nbit)
    global a b buffer buffersize fid; %#ok<GVMIS> 

    value = uint8(value);
    b = b-nbit; 
    buffer(a) = buffer(a) + bitshift(value,b,'uint8');

    if a==buffersize && b<=0
        fwrite(fid, buffer,'uint8');
        a = 0;
        buffer = zeros(1, buffersize, 'uint8');
    end

    if b==0
        a = a+1;
        b = 8;
    elseif b<0
        a = a+1;
        b = b+8;
        buffer(a) = buffer(a)+bitshift(value,b,'uint8');
    end

end
