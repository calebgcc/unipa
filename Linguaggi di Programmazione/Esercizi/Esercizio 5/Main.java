
import java.util.*;

class Punto{
    private double x;
    private double y;

    public Punto(double x, double y){
        this.x = x;
        this.y = y;
    }

    public final Double getX(){ return this.x; }
    public final Double getY(){ return this.y; }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || !(this.getClass().equals(o.getClass()))) return false;
        Punto p = (Punto) o;
        return this.getX().equals(p.getX()) && this.getY().equals(p.getY());
    }

    @Override
    public int hashCode(){
        return (int)(this.getX()*17+this.getY()*37);
    }

}

class Persona implements Comparable<Persona>{
    private String nome;
    private String cognome;
    private int eta;

    public Persona(String nome, String cognome, int eta){
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
    }

    public final String getNome(){ return this.nome; }
    public final String getCognome(){ return this.cognome; }
    public final int getEta(){ return this.eta; }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || !(this.getClass().equals(o.getClass()))) return false;
        Persona p = (Persona) o;
        return this.getNome().equals(p.getNome()) && this.getCognome().equals(p.getCognome()) && this.getEta()==p.getEta();
    }

    @Override
    public int hashCode(){
        return this.getNome().hashCode()*17+this.getCognome().hashCode()*37+this.getEta()*29;
    }

    @Override
    public String toString(){
        return this.cognome+" "+this.nome+" "+String.valueOf(this.eta);
    }

    @Override
    public int compareTo(Persona p){
        if(this.equals(p)) return 0;
        if(this.getCognome().equals(p.getCognome()))
            return this.getNome().compareTo(p.getNome());
        return this.getCognome().compareTo(p.getCognome());
    }


}

public class Main{

    /*
        Scrivere un metodoche verifichi, attraverso un ciclo for classico, 
        se un insieme di interi contiene due numeri consecutivi fra -r e r
    */
    public static boolean isInRange(int r, Set<Integer> s){
        boolean prev = false;
        for(int i=-r; i<=r; ++i){
            if(s.contains(i)){
                if(prev)
                    return true;
                else
                    prev = true;
            }
            else
                prev = false;
        }
        return false;
    }

    // scrivere una versione con for esteso e iteratore del metodo isInRange()
    public static boolean isInRangeFor(Set<Integer> s){
        int prev = Integer.MIN_VALUE;
        SortedSet<Integer> tSet = new TreeSet<>(s);
        for(Integer i : tSet){
            if(prev == Integer.MIN_VALUE)
                prev = i;
            else{
                if(prev+1 == i)
                    return true;
                prev = i;
            }
        }
        return false;
    }

    public static boolean isInRangeIt(Set<Integer> s){
        int prev = Integer.MIN_VALUE;
        int curr = 0;
        SortedSet<Integer> tSet = new TreeSet<>(s);
        Iterator it = tSet.iterator();
        while(it.hasNext()){
            curr = (Integer) it.next();
            if(prev != Integer.MIN_VALUE){
                if(curr == prev+1)
                    return true;
            }
            prev = curr;
        }
        return false;
    }

    // Scrivere un metodo che elimini tutti gli elementi pari da un insieme di interi
    public static void eliminaPari(Set<Integer> s){
       
        // Nun se po fa...
        // for(Integer i : s){
        //     if(i%2 == 0)
        //         s.remove(i);
        // }

        Iterator it = s.iterator();
        while(it.hasNext()){
            if(((Integer)it.next())%2 == 0)
                it.remove();
        }
    }

    // Scrivere un metodo che dato un insieme di punti stampi solo quelli del primo quadrante
    public static void stampaPrimoQuadrante(Set<Punto> s){
        for(Punto p : s){
            if(p.getX() >= 0.0 && p.getY() >= 0.0){
                System.out.print("("+p.getX()+","+p.getY()+") ");
            }
        }
        System.out.println("");
    }

    public static TreeSet<Persona> getTreeSet(HashSet<Persona> hs){
        return (new TreeSet<Persona>(hs));
    }

    // Scrivere un programma che generi un migliaio di numeri interi casuali nell’intervallo [0,9],
    // e mostri la frequenza con cui gli interi appaiono
    public static void showFrequency(int nTest){
        Random random = new Random();
        Map<Integer,Integer> map = new HashMap<>();
        int randomNumber;

        for(int i=0; i<nTest; ++i){
            randomNumber = random.nextInt(10);
            if(map.containsKey(randomNumber))
                map.put(randomNumber,map.get(randomNumber)+1);
            else
                map.put(randomNumber,1);
        }

        for(Map.Entry<Integer,Integer> pair : map.entrySet()){
            System.out.print("["+pair.getKey()+" | "+(double)pair.getValue()/nTest+"] ");
        }
        System.out.println("");

    }

    // Scrivere un metodo che prende a parametro una lista generica 
    // e restituisce una mappa che a ogni elemento della lista associa il suo codice hash
    public static <T extends Object> Map<Integer,T> listToMap(List<T> list){
        Map<Integer,T> map = new HashMap<>();
        for(T t : list){
            map.put(t.hashCode(),t);
        }
        return map;
    }

    // Scrivere un metodo che prende a parametro una mappa generica 
    // e restituisce un riferimento alla chiave con valore massimo nella mappa
    public static <K extends Object, V extends Comparable<? super V>> K maxValueMap(Map<K,V> map){
        Map.Entry<K,V> maxUntilNow = null;
        for(Map.Entry<K,V> pair : map.entrySet()){
            if(maxUntilNow == null){
                maxUntilNow = pair;
            }
            else{
                if(pair.getValue().compareTo(maxUntilNow.getValue()) > 0)
                    maxUntilNow = pair;
            }
        }
        return (maxUntilNow != null ? maxUntilNow.getKey():null);
    }


    public static void main(String[] args){

        System.out.println(
            isInRange(10,Set.of(-2,5,6,11,12))
        ); // true

        System.out.println(
            isInRangeFor(Set.of(-2,5,6))
        ); // true

        System.out.println(
            isInRangeIt(Set.of(-2,5,6))
        ); // true

        Set<Integer> s = new HashSet<>();
        s.add(2); s.add(3); s.add(5); s.add(6); s.add(7);
        eliminaPari(s);
        System.out.println(s);

        stampaPrimoQuadrante(Set.of(
            new Punto(2.0,1.0),
            new Punto(-1.5,-2.4),
            new Punto(2.0,-1.0),
            new Punto(3.6,7.3),
            new Punto(-3.2,1.0)
        ));


        HashSet<Persona> hs = new HashSet<>();
        hs.add(new Persona("Alex","",14));
        hs.add(new Persona("X","Y",10));
        hs.add(new Persona("Pablo","Picasso",66));
        hs.add(new Persona("Alex","",14)); // duplicato che non verrà aggiunto

        System.out.println(hs);
        System.out.println(getTreeSet(hs));

        showFrequency(1000);

        System.out.println(listToMap(
            List.of("caleb","gucciardi","ciao","miao")
        ));

        System.out.println(
            maxValueMap(
                listToMap(
                    List.of("caleb","gucciardi","ciao","miao")
                )
            )
        );

    }
}