package jalgo;


import java.util.Arrays;
import java.util.Random;

import jalgo.algorithms.Quick;

public class Main{
    
    public static void main(String[] args){
        
        int sizeList[] = {5,10,20,30};
        for(int SIZE : sizeList){
            Integer[] arr = new Integer[SIZE];
            
            for(int i=0; i<SIZE; ++i)
                arr[i] = new Random().nextInt(100);
            
            Quick.sort(arr,true);
            System.out.println(Arrays.toString(arr));
        }
    }
}