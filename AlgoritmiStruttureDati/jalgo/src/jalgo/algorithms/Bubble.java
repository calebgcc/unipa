package jalgo.algorithms;

import java.util.Comparator;

public class Bubble {
    public static <T extends Comparable<? super T>> void sort(T[] arr){
        sort(arr,false);
    }

    public static <T extends Comparable<? super T>> void sort(T[] arr, boolean descending){
        int reverse = descending ? -1:1;
        bubbleSort(arr,(a,b)->a.compareTo(b)*reverse);
    }

    public static <T> void sort(T[] arr, Comparator<T> comp){
        sort(arr,comp,false);
    }

    public static <T> void sort(T[] arr, Comparator<T> comp,boolean descending){
        int reverse = descending ? -1:1;
        bubbleSort(arr,(a,b)->comp.compare(a,b)*reverse);
    }
    
    
    private static <T> void bubbleSort(T[] arr, Comparator<T> comp){
        boolean flag = true;
        int N = arr.length;
        while(flag){
            flag = false;
            for(int i=0; i<N-1; ++i){
                if(comp.compare(arr[i],arr[i+1]) > 0){
                    T temp = arr[i+1];
                    arr[i+1] = arr[i];
                    arr[i] = temp;
                    flag = true;
                }
            }
            --N;
        }
    }
}
