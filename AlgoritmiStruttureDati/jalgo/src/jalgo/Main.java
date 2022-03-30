package jalgo;


import java.util.Arrays;
import java.util.Random;

import jalgo.algorithms.Selection;

public class Main{
    
    public static void main(String[] args){
        int SIZE = 30;
        Integer[] arr = new Integer[SIZE];
        
        for(int i=0; i<SIZE; ++i)
            arr[i] = new Random().nextInt(100);
        
        Selection.sort(arr);

        System.out.println(Arrays.toString(arr));

    }
}