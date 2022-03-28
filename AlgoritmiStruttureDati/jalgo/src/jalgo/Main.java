package jalgo;


import java.util.Arrays;
import java.util.Random;

import jalgo.algorithms.Jalgo;

public class Main{
    
    public static void main(String[] args){
        int SIZE = 101;
        Integer[] arr = new Integer[SIZE];
        
        for(int i=0; i<SIZE; ++i){
            arr[i] = new Random().nextInt(100);
            if(i%2 == 0)
                arr[i]*=-1;
        }

        Jalgo.mergeSort(arr,(a,b)->a.compareTo(b),true);

        System.out.println(Arrays.toString(arr));

    }
}