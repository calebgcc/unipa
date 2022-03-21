package jalgo;

import java.util.Arrays;
import jalgo.algorithms.Jalgo;

public class Main{
    public static void main(String[] args){ 

        Integer a[] = {1,2,3,4,5,6,7,8,9,0};
        Jalgo.selectionSort(a,(p,q) -> p.compareTo(q),true);
        System.out.println(
            Arrays.toString(a)
        );
    }
}