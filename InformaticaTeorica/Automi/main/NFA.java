package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NFA{
    private String label;
    private boolean iniziale;
    private boolean finale;
    private Map<Character,Set<NFA>> transizioni;

    public NFA(String label, boolean iniziale, boolean finale){
        this.label = label;
        this.iniziale = iniziale;
        this.finale = finale;
        this.transizioni = new HashMap<>();
    }

    public NFA(String label){
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

    public Map<Character,Set<NFA>> getTransizioni() {
        return transizioni;
    }

    public void link(char ch, NFA ...sVet){
        Set<NFA> tr = this.transizioni.get(ch);
        if(tr == null){
            tr = new HashSet<NFA>();
        }
        for(NFA s : sVet)
            tr.add(s);
        this.transizioni.put(ch,tr);
    }

    @Override
    public String toString(){
        if(this.isFinale())
            return this.getLabel()+"°";
        return this.getLabel();
    }
    
    public static boolean isAccepted(Set<NFA> s, String w)throws IllegalArgumentException{
        return NFA.isAccepted(s, w,false);
     }

    public static boolean isAccepted(Set<NFA> sSet,String w, boolean verbose)throws IllegalArgumentException{
        
        if(sSet == null){
            throw new IllegalArgumentException();
        }

        for(NFA s : sSet)
            if(!s.isIniziale())
                throw new IllegalArgumentException();

        return NFA.recursion(sSet,w,0,verbose);
     
    }

    private static boolean recursion(Set<NFA> sSet,String w,int i, boolean verbose){
        
        if(sSet == null)
            return false;
        if(i >= w.length()){
            for(NFA s : sSet){
                if(s.isFinale())
                    return true;
            }
            return false;
        }
            
        
        for(NFA s : sSet){
            if(NFA.recursion(s.getTransizioni().get(w.charAt(i)), w, i+1, verbose))
                return true;
        }

        return false;
    }

}
