function output=ham2rgb(path)
    output = zeros(512, 512, 3, 'uint8');
    cmap = zeros(16,3,'uint8');
    l = [1 1];

    global buffer idx btr; %#ok<GVMIS> 
    fid = fopen(path, 'r');
    buffer = fread(fid, 'uint8');
    fclose(fid);
    idx = 1; % buffer index
    btr = 8; % bit rimansti da leggere in buffer(idx)

    for i=1:16
        cmap(i,1) = reader(4);
        cmap(i,2) = reader(4);
        cmap(i,3) = reader(4);
    end

    for h=1:size(output, 1)
        for w=1:size(output,2)

            control = reader(2);
            data = reader(4);

            if control == 0
                output(h,w,:) = cmap(data+1, :);
            else
                output(h,w,:) = output(l(1),l(2),:);
                output(h,w,control) = data;
            end

            l = [h w];
        end
    end
    output = output*17;
end


function result=reader(nbit)
    global buffer idx btr; %#ok<GVMIS> 

    %disp(">>>> I <<<<");
    %nbit
    %btr
    %idx
    %disp(dec2bin(buffer(idx),8));

    sm = 8 - nbit; % shift mask
    mask = bitshift(uint8(255),sm);
    result = bitand(buffer(idx), mask);
    result = bitshift(result,-sm);


    if nbit > btr
        nbit = nbit - btr;
        idx = idx+1;
        btr = 8;
        result = result+reader(nbit); 
    else
        btr = btr - nbit;
        buffer(idx) = bitshift(buffer(idx), nbit, 'uint8');
        if btr == 0
            btr = 8;
            idx = idx+1;
        end
    end

    %result
    %disp(dec2bin(result,8));
    %disp("<<<< F >>>>");

end