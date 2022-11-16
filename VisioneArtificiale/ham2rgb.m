function output=ham2rgb(img,cmap)
    output = uint8(zeros(size(img,2), size(img,1), 3));
    l = [1 1];
    start = 2;
    output(1,1,:) = cmap(img(1,1)+1,:);
    for h=1:size(img, 2)
        for w=start:size(img,1)
            if bitand(48,img(h,w)) == 0 
                % color of the cmap
                data = bitand(15, img(h,w));
                output(h,w,:) = cmap(data+1, :);
            elseif bitand(48,img(h,w)) == 16
                % color of the previous with red changed
                output(h,w,:) = output(l(1),l(2),:);
                output(h,w,1) = bitand(15,img(h,w));
            elseif bitand(48,img(h,w)) == 32
                % color of the previous with green changed
                output(h,w,:) = output(l(1),l(2),:);
                output(h,w,2) = bitand(15,img(h,w));
            else
                % color of the previous with blue changed
                output(h,w,:) = output(l(1),l(2),:);
                output(h,w,3) = bitand(15,img(h,w));
            end 
            l = [h w];
        end
        start = 1;
    end
    output = output*17;
end
