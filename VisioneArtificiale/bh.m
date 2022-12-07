function output=bh(img, se, ce)
    %{
       (esempio)
       >>  output = bh(img, [0 1 0; 1 1 1; 0 1 0], []);
       >>  output = bh(img, [0 1 0; 1 1 1; 0 1 0], [1,1]);
    %}
    output = chiusura(img, se, ce) - img;
end