function output=conv_generic(img,kernel)
    % flip kernel sulle due assi per la convoluzione
    kernel = flip(kernel, 1);
    kernel = flip(kernel, 2);

    center = round(size(kernel)/2); % centro del kernel

    pva = center(1)-1; % padding vertical alto
    pvb = size(kernel,1) - center(1); % padding verticale basso
    pos = center(2)-1; % padding orizzontale sinistro
    pod = size(kernel,2) - center(2); % padding orizzontale destro

    % area di lavoro (immersione)
    % una matrice che ha la stezza size di img (dove tutti sono 1)
    % e che ha un padding alto/basso sinistra/destra di 0
    imm = zeros( ...
        size(img,1)+pva+pvb, ...
        size(img,2)+pos+pod ...
        );
    imm(pva+1:size(img,1)+pva, pos+1:size(img,2)+pos) = ones(size(img));
    range = find(imm); % indici di dove si trovera' l'immagine
    imm(range) = img;
    temp = single(ones(size(imm)));
    H = size(imm, 1);
    W = size(imm, 2);

    % mappa: pre calcolare i prodotti
    prodmap = (0:255)' * kernel(:)';

    % mappa: indice kernel => indice nell'immagine
    i = 1;
    lut = zeros(size(kernel,1)*size(kernel,2),1); 
    for w=1:size(kernel,2)
        for h=1:size(kernel,1)
            dir = [h,w] - center; % direzione dell'elemento (h,w) rispetto al centro
            lut(i) = H*dir(2)+dir(1);
            i=i+1;
        end
    end
    
    % convoluzione
    for i=range'
        acc = single(0);
        for k=(1:(size(kernel,1)*size(kernel,2)))
            acc = acc + prodmap(imm(i + lut(k))+1,k);
        end
        temp(i) = acc; 
    end

    output = temp(pva+1:size(img,1)+pva, pos+1:size(img,2)+pos);
