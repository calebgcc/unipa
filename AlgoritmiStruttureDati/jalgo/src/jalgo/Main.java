package jalgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import jalgo.algorithms.Jalgo;

public class Main{

    private static final int SIZE = 31;
    private static final int MAX = 5;
    private static final int MIN = -5;

    public static void main(String[] args){ 

        List<Integer> list = new ArrayList<>();
        Random generator = new Random();

        for(int i=0;i<SIZE; ++i)
            list.add(MIN + generator.nextInt(MAX-MIN));
        
        Collections.shuffle(list);

        Integer[] arr = list.stream().toArray(Integer[]::new);

        Jalgo.heapSort(arr,true);
        System.out.println(Arrays.toString(arr));
    }
}