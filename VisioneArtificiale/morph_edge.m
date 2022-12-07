function output=morph_edge(img)
    %{
       (esempio)
       >>  output = morph_edge(img);
    %}
    se = [0 1 0; 1 1 1; 0 1 0]; % uso di default quello che restitusice bordi "sottili"
    output = dilatazione(img, se, []) - erosione(img, se, []);
end