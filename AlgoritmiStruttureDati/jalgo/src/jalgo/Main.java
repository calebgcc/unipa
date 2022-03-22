package jalgo;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import jalgo.algorithms.Jalgo;

public class Main{
    public static void main(String[] args){ 

        final int SIZE = 100_000;

        Double a[] = new Double[SIZE];
        Double b[] = new Double[SIZE];
        Double c[] = new Double[SIZE];

        Random generator = new Random();
        final double MIN = -100_000;
        final double MAX = 100_000;
        
        for(int i=0; i<SIZE; ++i)
            a[i] = b[i] = c[i] = MIN + (MAX-MIN)*generator.nextDouble();

        Instant start,end;

        start = Instant.now();
        Jalgo.selectionSort(a);
        end = Instant.now();
        System.out.println("Selection Sort :: "+Duration.between(start, end).toString());

        start = Instant.now();
        Jalgo.insertionSort(b);
        end = Instant.now();
        System.out.println("Insertion Sort :: "+Duration.between(start, end).toString());

        start = Instant.now();
        Jalgo.bubbleSort(c);
        end = Instant.now();
        System.out.println("Bubble Sort :: "+Duration.between(start, end).toString());

    }
}