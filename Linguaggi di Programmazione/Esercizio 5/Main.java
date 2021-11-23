
import java.util.*;

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


    }
}