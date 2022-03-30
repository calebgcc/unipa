package jalgo.algorithms;

import java.util.Comparator;

public class Jalgo {

    /*  B I N A R Y  -  S E A R C H  */
    public static <T extends Comparable<? super T>> int binarySearch(T[] arr, T target){
        return binarySearch(arr, target,false);
    }

    // isReverse = true if array is sorted in descending order
    public static <T extends Comparable<? super T>> int binarySearch(T[] arr, T target,boolean isReverse){
        int low = 0, high = arr.length-1;
        int reverse = isReverse ? -1:1;
        int mid;
        while(low<=high){
            mid = (low+high)/2;
            if(arr[mid].compareTo(target) == 0)
                return mid;
            else if(arr[mid].compareTo(target)*reverse > 0)
                high = mid-1;
            else 
                low = mid+1;
        }
        return -1;
    } 

    public static <T> int binarySearch(T[] arr, T target, Comparator<T> comp){
        return binarySearch(arr, target,comp,false);
    }

    public static <T> int binarySearch(T[] arr, T target, Comparator<T> comp, boolean isReverse){
        int low = 0, high = arr.length-1;
        int reverse = isReverse ? -1:1;
        int mid;
        while(low<=high){
            mid = (low+high)/2;
            if(comp.compare(arr[mid],target) == 0)
                return mid;
            else if(comp.compare(arr[mid],target)*reverse > 0)
                high = mid-1;
            else 
                low = mid+1;
        }
        return -1;
    } 

    /* M E R G E - S O R T */
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
    
    private static <T> void mergeSortSubRoutine(T[] arr,int low, int high,Comparator<T> comp){
        if((high-low+1) <= 1)
            return;
        int mid = (low+high)/2;
        mergeSortSubRoutine(arr, low, mid, comp);
        mergeSortSubRoutine(arr, mid+1, high, comp);
        merge(arr, low, mid+1, high, comp);
    }
    
    public static <T extends Comparable<? super T>>  void mergeSort(T[] arr){
        mergeSort(arr,false);
    }
    
    public static <T extends Comparable<? super T>>  void mergeSort(T[] arr,boolean descending){
        int reverse = descending ? -1:1;
        mergeSortSubRoutine(arr,0,arr.length-1,(a,b)->a.compareTo(b)*reverse);
    }
    
    public static <T> void mergeSort(T[] arr, Comparator<T> comp){
        mergeSort(arr,comp,false);
    }

    public static <T> void mergeSort(T[] arr, Comparator<T> comp, boolean descending){
        int reverse = descending ? -1:1;
        mergeSortSubRoutine(arr, 0, arr.length-1, (a,b)->comp.compare(a, b)*reverse);
    }

    
}
