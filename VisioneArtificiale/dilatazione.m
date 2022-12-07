function output=dilatazione(img,se, ce)
    %{
       (esempio)
       >>  output = dilatazione(img, [0 1 0; 1 1 1; 0 1 0], []);
       >>  output = dilatazione(img, [0 1 0; 1 1 1; 0 1 0], [1,1]);
    %}

    
    [h,w] = size(se); % dimensione del se

    if isempty(ce)
        center = round([h,w]/2); % se non viene passato il centro lo calcoliamo
    else
        center = ce;
    end

    pva = center(1)-1; % padding vertical alto
    pvb = h - center(1); % padding verticale basso
    pos = center(2)-1; % padding orizzontale sinistro
    pod = w - center(2); % padding orizzontale destro
    
    % matrice di immersione
    % usiamo 0 cosi che il massimo tra 0 e un livello di-
    % grigio sia il livello di grigio
    imm = zeros(size(img,1)+pva+pvb, size(img,2)+pos+pod, 'uint8');

    % immergiamo la nostra immagine
    imm(pva+1:size(img,1)+pva, pos+1:size(img,2)+pos) = ones(size(img));
    range = find(imm==1); % troviamo le coordinate dell'immagine
    imm(range) = img;
    temp = zeros(size(imm), 'uint8');
    [H,~] = size(imm); % dimensione dell'immersione

    % calcoliamo la look-up table
    range_se = find(se); % troviamo gli 1 dello se
    lut = zeros(size(range_se)); 
    lind = 1; % lut index
    for i=range_se'
        % dato un singolo indice dobbiamo trovarci le coordiante r c
        c = floor((i-1)/h)+1;
        r = mod(i-1,h)+1;

        dir = [r,c] - center; % troviamo le direzione di [r,c] rispetto al center
        % calcoliamo quanto dobbiamo sommare ad un indice x (dell'immagine) 
        % per spostarci nella direzione di i 
        lut(lind) = H*dir(2) + dir(1); 
        lind = lind+1;
    end


    % per ogni indice dell'immagine: 
    % 1.  prendiamo tutti i livelli di grigio corrispondenti agli 1 del se
    %     considerando i come il center
    % 2.  ne facciamo il massimo
    % 3.  assegniamo il massimo a temp(i)
    for i=range'
        temp(i) = max(imm(lut+i));
    end

    output = temp(range); % inseriamo in output l'immagine
    % ne facciamo il reshape
    output = reshape(output, size(img,1), size(img,2)); 
end