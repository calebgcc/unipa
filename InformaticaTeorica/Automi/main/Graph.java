package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Graph{
    private Set<Character> alphabet;
    private Set<Integer> initials;
    private Set<Integer> finals;
    private Map<Integer, Map<Character,Set<Integer>>> transitions;

    public Graph(int numberOfNodes) throws IllegalArgumentException{
        
        if(numberOfNodes <= 0)
            throw new IllegalArgumentException();

        this.alphabet = new HashSet<>();
        this.initials = new HashSet<>();
        this.finals = new HashSet<>();
        this.transitions = new HashMap<>();

        for(int i=1; i<=numberOfNodes;++i)
            this.transitions.put(i,null);
    }

    public void set(int state, State type){
        switch(type){
            case INITIAL:
                this.initials.add(state);
                break;
            case FINAL:
                this.finals.add(state);
                break;
            default:
            break;
        }
    }

    public void link(int s1, char symbol, int s2){
        this.link(s1,symbol,Set.of(s2));
    }

    public void link(int s1,char symbol, Set<Integer> sSet) throws IllegalArgumentException{
        
        if(sSet == null || sSet.isEmpty()){
            throw new IllegalArgumentException("Stati arrivo transizione null");
        }
        if(s1 <= 0 || s1 > this.transitions.size()){
            throw new IllegalArgumentException("Questo stato non esiste");
        }

        for(Integer state : sSet){
            if(state <= 0 || state > this.transitions.size()){
                throw new IllegalArgumentException("Questo stato non esiste");
            }
        }

        
        Map<Character,Set<Integer>> map = this.transitions.get(s1);
        if(map == null){
            map = new HashMap<>();
        } 

        Set<Integer> set = map.get(symbol);
        if(set == null){
            set = new HashSet<>();
        }

        set.addAll(sSet);
        map.put(symbol,set);
        this.transitions.put(s1,map);

        this.alphabet.add(symbol);
    }


    private void spaces(int n){
        for(int i=0; i<n; ++i)
            System.out.print("  |");
        System.out.print("-");
    }



    public boolean run(String w){
        return this.recursion(this.initials,w,0,false);
    }

    public boolean run(String w, boolean verbose){
        return this.recursion(this.initials,w,0,verbose);
    }

    public boolean recursion(Set<Integer> set,String w,int i,boolean verbose){

        if(set == null || set.isEmpty())
            return false;
        if(i >= w.length()){
            for(Integer state : set){
                if(this.finals.contains(state))
                    return true;
            }
            return false;
        }   
        
        for(Integer state : set){
            Map<Character,Set<Integer>> m = this.transitions.get(state);
            if(m != null){
                Set<Integer> s = m.get(w.charAt(i));
                if(s == null && verbose){
                    this.spaces(i);
                    System.out.println("( "+state+" , "+w.charAt(i)+" ) => "+"pozzo");
                }
                else{
                    if(verbose){
                        this.spaces(i);
                        System.out.println("( "+state+" , "+w.charAt(i)+" ) => "+s);
                    }
                    if(this.recursion(m.get(w.charAt(i)), w, i+1,verbose))
                        return true;
                }
            }
            else if(verbose){
                this.spaces(i);
                System.out.println("( "+state+" , "+w.charAt(i)+" ) => "+"pozzo");
            }
        }

        return false;
    }

    public Set<Character> getAlphabet() {
        return alphabet;
    }

    public Set<Integer> getInitials() {
        return initials;
    }


    public Set<Integer> getFinals() {
        return finals;
    }

    @Override 
    public String toString(){
        return this.transitions.toString();
    }
    

}
