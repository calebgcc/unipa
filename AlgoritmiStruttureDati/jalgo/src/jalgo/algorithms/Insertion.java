package jalgo.algorithms;

import java.util.Comparator;

public class Insertion {
    public static <T extends Comparable<? super T>> void sort(T[] arr){
        sort(arr,false);
    }

    public static <T extends Comparable<? super T>> void sort(T[] arr, boolean descending){
        int reverse = descending ? -1:1;
        insertionSort(arr, (a,b)->a.compareTo(b)*reverse);
    }

    public static <T> void sort(T[] arr, Comparator<T> comp){
        sort(arr,comp,false);
    }

    public static <T> void sort(T[] arr, Comparator<T> comp, boolean descending){
        int reverse = descending ? -1:1;
        insertionSort(arr, (a,b)->comp.compare(a,b)*reverse);
    }
    
    private static <T> void insertionSort(T[] arr, Comparator<T> comp){
        int j;
        T temp;
        for(int i=1; i<arr.length; ++i){
            temp = arr[i];
            for(j=i-1;j>=0 && (comp.compare(arr[j],temp) > 0);--j)
                arr[j+1] = arr[j];
            arr[j+1] = temp;
        }
    }
}
