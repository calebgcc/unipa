package main;

import java.util.Map;

public class Stato {
    private String label;
    private boolean iniziale;
    private boolean finale;
    private Map<Character,Stato> transizioni;
    
    public Stato(String label, boolean iniziale, boolean finale){
        this.label = label;
        this.iniziale = iniziale;
        this.finale = finale;
    }
    public Stato(String label,boolean iniziale){
        this.label = label;
        this.inziale = iniziale;
    }
    public Stato(String label,boolean finale){
        this.label = label;
        this.finale = finale;
    }
    
    
}
