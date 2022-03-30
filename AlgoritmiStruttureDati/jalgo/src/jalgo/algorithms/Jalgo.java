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
}
