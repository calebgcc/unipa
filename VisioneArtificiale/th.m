function output=th(img, se, ce)
    %{
       (esempio)
       >>  output = th(img, [0 1 0; 1 1 1; 0 1 0], []);
       >>  output = th(img, [0 1 0; 1 1 1; 0 1 0], [1,1]);
    %}
    output = img - apertura(img, se, ce);
end