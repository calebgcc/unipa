package jalgo;

import java.util.Arrays;
import jalgo.algorithms.Jalgo;

public class Main{
    public static void main(String[] args){ 

        Integer arr[] = {1,0,1,0,2,0,1,3,5,7};
        Jalgo.insertionSort(arr,(a,b) -> a.compareTo(b));
        System.out.println(
            Arrays.toString(arr)
        );
    }
}