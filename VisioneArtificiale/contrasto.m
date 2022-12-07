function output=contrasto(img, se, ce)
    %{
       (esempio)
       >>  output = contrasto(img, ones(3), []); % poco contrasto
       >>  output = contrasto(img, ones(9), []); % molto contrasto
    %}
    output = uint8( max(0, min(255, single(img) + single(th(img,se,ce)) - single(bh(img,se,ce)) )));
end