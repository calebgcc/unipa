package main;

import java.util.HashMap;
import java.util.Map;

public class DFA{
    private String label;
    private boolean iniziale;
    private boolean finale;
    private Map<Character,DFA> transizioni;
    
    public DFA(String label, boolean iniziale, boolean finale){
        this.label = label;
        this.iniziale = iniziale;
        this.finale = finale;
        this.transizioni = new HashMap<>();
    }
    public DFA(String label){
        this(label,false,false);
    }
    public String getLabel() {
        return label;
    }
    public boolean isIniziale() {
        return iniziale;
    }
    public boolean isFinale() {
        return finale;
    }
    public Map<Character, DFA> getTransizioni() {
        return transizioni;
    }

    public void link(char ch, DFA s){
        this.transizioni.put(ch, s);
    }

    @Override
    public String toString(){
        if(this.isFinale())
            return this.getLabel()+"°";
        return this.getLabel();
    }
    
    public static boolean isAccepted(DFA s,String w, boolean verbose)throws IllegalArgumentException{
        
        if(s == null){
            throw new IllegalArgumentException();
        }
        if(!s.isIniziale()){
           throw new IllegalArgumentException("Lo stato non è uno stato iniziale");
        }

        for(char ch : w.toCharArray()){
            if(verbose) System.out.print("( "+s+","+ch+" )");
            s = s.getTransizioni().get(ch);
            if(s == null){
                if(verbose) System.out.println(" => pozzo");
                return false;
            }
            if(verbose) System.out.println(" => "+s);
        }
        return s.isFinale();
    }

    public static boolean isAccepted(DFA s, String w)throws IllegalArgumentException{
       return DFA.isAccepted(s, w,false);
    }
    
}
