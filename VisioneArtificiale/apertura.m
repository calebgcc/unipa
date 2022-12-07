function output=apertura(img, se, ce)
    %{
       (esempio)
       >>  output = apertura(img, [0 1 0; 1 1 1; 0 1 0], []);
       >>  output = apertura(img, [0 1 0; 1 1 1; 0 1 0], [1,1]);
    %}
    e = erosione(img, se, ce);

    % operatore cappello
    se = flip(se, 1);
    se = flip(se, 2);

    output = dilatazione(e, se, ce);
end