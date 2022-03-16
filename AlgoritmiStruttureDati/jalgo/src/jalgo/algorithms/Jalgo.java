package jalgo.algorithms;

public class Jalgo {

   public static <T extends Comparable<? super T>> int binarySearch(T[] arr, T target){
       int low = 0, high = arr.length-1;
       int mid;
       while(low<=high){
           mid = (low+high)/2;
           if(arr[mid].compareTo(target) == 0)
            return mid;
           else if(arr[mid].compareTo(target) > 0)
            high = mid-1;
           else 
            low = mid+1;
       }
       return -1;
   } 
      
}
