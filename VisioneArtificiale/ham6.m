function [output, cmap]=ham6(img)
    img4096 = round(img/17); % img with 16*16*16 colors
    [output, cmap] = rgb2ind(img4096*17,16);
    cmap = round((cmap*255)/17); % each color is now between 0 and 255

    last = single(cmap(output(1,1)+1, :)); % last rgb value
    start = 2;
    for h=1:size(img4096, 2)
        for w=start:size(img4096,1)
            
            real_color = single(reshape(img4096(h,w,:),1,[]));
          
            % norma minima
            rc = repmat(real_color,16,1);
            d = sqrt(sum((rc-single(cmap)).^2,2));
            [dm,mind] = min(d);

            % change the red
            d1 = norm(single(real_color - [real_color(1) last(2) last(3)]));

            % change green
            d2 = norm(single(real_color - [last(1) real_color(2) last(3)]));
        
            % change blue
            d3 = norm(single(real_color - [last(1) last(2) real_color(3)]));

            vet = [dm, d1, d2, d3];
            [~,i] = min(vet);
        
        
            if i==1
                % control 00
                output(h,w) = uint8(mind-1);
                last = single(cmap(mind, :));
            elseif i==2
                % control 01
                output(h,w) = 16 + uint8(real_color(1));
                last = [real_color(1) last(2) last(3)];
            elseif i==3
                % control 10
                output(h,w) = 32 + uint8(real_color(2));
                last = [last(1) real_color(2) last(3)];
            else
                % control 11
                output(h,w) = 48 + uint8(real_color(3));
                last = [last(1) last(2) real_color(3)];
            end

        end
        start = 1;
    end
end
