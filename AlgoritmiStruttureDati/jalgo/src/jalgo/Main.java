package jalgo;


import java.util.Arrays;
import java.util.Random;

import jalgo.algorithms.Insertion;
import jalgo.algorithms.Selection;
import jalgo.algorithms.Bubble;
import jalgo.algorithms.Heap;
import jalgo.algorithms.Merge;

public class Main{
    
    public static void main(String[] args){
        int SIZE = 2;
        Integer[] arr = new Integer[SIZE];
        
        for(int i=0; i<SIZE; ++i)
            arr[i] = new Random().nextInt(100);
        
        //Selection.sort(arr,true);
        //Insertion.sort(arr,true);
        //Bubble.sort(arr);
        //Heap.sort(arr);
        Merge.sort(arr,true);

        System.out.println(Arrays.toString(arr));

    }
}