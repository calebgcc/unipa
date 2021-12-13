package main;

public class Persona {
    private char genere;
    private int eta;

    public Persona(char genere, int eta){
        this.genere = genere;
        this.eta = eta;
    }

    public char getGenere() {
        return genere;
    }

    public int getEta() {
        return eta;
    }
    
}
