function output=chiusura(img, se, ce)
    %{
       (esempio)
       >>  output = chiusura(img, [0 1 0; 1 1 1; 0 1 0], []);
       >>  output = chiusura(img, [0 1 0; 1 1 1; 0 1 0], [1,1]);
    %}
    d = dilatazione(img, se, ce);

    % operatore cappello
    se = flip(se, 1);
    se = flip(se, 2);

    output = erosione(d, se, ce);
end