package jalgo.algorithms;

import java.util.Comparator;

public class Selection {
    public static <T extends Comparable<? super T>> void sort(T[] arr){
        sort(arr,false);
    }
        
    public static <T extends Comparable<? super T>> void sort(T[] arr, boolean descending){
        int reverse = descending ? -1:1;
        selectionSort(arr, (a,b)->a.compareTo(b)*reverse);
    }

    public static <T> void sort(T[] arr, Comparator<T> comp){
        sort(arr,comp,false);
    }
        
    public static <T> void sort(T[] arr, Comparator<T> comp, boolean descending){
        int reverse = descending ? -1:1;
        selectionSort(arr,(a,b)->comp.compare(a,b)*reverse); 
    }        
    public static <T> void selectionSort(T[] arr, Comparator<T> comp){
        for(int i=0; i<arr.length-1; ++i){
            int min = i;
            for(int j=i+1;j<arr.length; ++j)
                min = (comp.compare(arr[min],arr[j]) < 0) ? min:j;
            // swap
            T temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }
}    
