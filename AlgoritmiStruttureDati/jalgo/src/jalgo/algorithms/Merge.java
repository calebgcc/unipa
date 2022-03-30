package jalgo.algorithms;

import java.util.Comparator;

public class Merge {
    @SuppressWarnings("unchecked")
    private static <T> void merge(T[] arr, int a, int b, int c,Comparator<T> comp){
       Object[] vet = new Object[c-a+1];
       int i=0, j=a, k=b;
       while(j<b && k<=c){
            if(comp.compare(arr[j], arr[k]) < 0)
                vet[i++] = arr[j++];
            else
                vet[i++] = arr[k++];
       }  
       
        while(j<b)
            vet[i++] = arr[j++];
        while(k<=c)
            vet[i++] = arr[k++];
        
        j = a;
        for(i=0;i<vet.length;++i,++j)
            arr[j] = (T) vet[i];
    }
    
    private static <T> void mergeSort(T[] arr,int low, int high,Comparator<T> comp){
        if((high-low+1) <= 1)
            return;
        int mid = (low+high)/2;
        mergeSort(arr, low, mid, comp);
        mergeSort(arr, mid+1, high, comp);
        merge(arr, low, mid+1, high, comp);
    }
    
    public static <T extends Comparable<? super T>>  void sort(T[] arr){
        sort(arr,false);
    }
    
    public static <T extends Comparable<? super T>>  void sort(T[] arr,boolean descending){
        int reverse = descending ? -1:1;
        mergeSort(arr,0,arr.length-1,(a,b)->a.compareTo(b)*reverse);
    }
    
    public static <T> void sort(T[] arr, Comparator<T> comp){
        sort(arr,comp,false);
    }

    public static <T> void sort(T[] arr, Comparator<T> comp, boolean descending){
        int reverse = descending ? -1:1;
        mergeSort(arr, 0, arr.length-1, (a,b)->comp.compare(a, b)*reverse);
    }
}
