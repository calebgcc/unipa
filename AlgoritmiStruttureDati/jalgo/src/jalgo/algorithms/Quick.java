package jalgo.algorithms;

import java.util.Comparator;

public class Quick {
    
    public static <T extends Comparable<? super T>> void sort(T[] arr){
        sort(arr,false);
    }

    public static <T extends Comparable<? super T>> void sort(T[] arr,boolean descending){
        int reverse = descending ? -1:1;
        quickSort(arr,0,arr.length-1,(a,b)->a.compareTo(b)*reverse);
    }

    public static <T> void sort(T[] arr, Comparator<T> comp){
        sort(arr,comp,false);
    }
    public static <T> void sort(T[] arr, Comparator<T> comp,boolean descending){
        int reverse = descending ? -1:1;
        quickSort(arr,0,arr.length-1,(a,b)->comp.compare(a, b)*reverse);
    }
    
    private static <T> void quickSort(T[] arr,int low, int high,Comparator<T> comp){
        if(low>=high) return;
        int div = partition(arr,low,high,comp);
        quickSort(arr, low, div-1, comp);
        quickSort(arr, div+1, high, comp);
    }
    
    private static <T> void swap(T[] arr, int a,int b){
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static <T> int partition(T[] arr,int low, int high,Comparator<T> comp){
        int pivot = low, inf=low, sup=high;
        while(inf<sup){
            while(inf<=high && comp.compare(arr[inf], arr[pivot]) <= 0) ++inf;
            while(sup>=low && comp.compare(arr[sup], arr[pivot]) > 0) --sup;
            if(inf>=sup) break;
            swap(arr,inf,sup);
        }
        swap(arr,pivot,sup);
        return sup;
    }
}
