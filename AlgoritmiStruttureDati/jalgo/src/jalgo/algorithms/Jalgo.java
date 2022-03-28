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

    /* S E L E C T I O N  - S O R T  */
    public static <T extends Comparable<? super T>> void selectionSort(T[] arr){
        selectionSort(arr,false);
    }
      
    public static <T extends Comparable<? super T>> void selectionSort(T[] arr, boolean descending){
        int desc = descending ? -1:1;
        for(int i=0; i<arr.length-1; ++i){
            int min = i;
            for(int j=i+1;j<arr.length; ++j)
                min = (arr[min].compareTo(arr[j])*desc < 0) ? min:j;
            // swap
            T temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    public static <T> void selectionSort(T[] arr, Comparator<T> comp){
        selectionSort(arr,comp,false);
    }
      
    public static <T> void selectionSort(T[] arr, Comparator<T> comp, boolean descending){
        int desc = descending ? -1:1;
        for(int i=0; i<arr.length-1; ++i){
            int min = i;
            for(int j=i+1;j<arr.length; ++j)
                min = (comp.compare(arr[min],arr[j])*desc < 0) ? min:j;
            // swap
            T temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    /* I N S E R T I O N  - S O R T*/
    public static <T extends Comparable<? super T>> void insertionSort(T[] arr){
        insertionSort(arr,false);
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] arr, boolean descending){
        int j;
        T temp;
        int reverse = descending ? -1:1;
        for(int i=1; i<arr.length; ++i){
            temp = arr[i];
            for(j=i-1;j>=0 && (arr[j].compareTo(temp)*reverse > 0);--j)
                arr[j+1] = arr[j];
            arr[j+1] = temp;
        }
    }

    public static <T> void insertionSort(T[] arr, Comparator<T> comp){
        insertionSort(arr,comp,false);
    }

    public static <T> void insertionSort(T[] arr, Comparator<T> comp, boolean descending){
        int j;
        T temp;
        int reverse = descending ? -1:1;
        for(int i=1; i<arr.length; ++i){
            temp = arr[i];
            for(j=i-1;j>=0 && (comp.compare(arr[j],temp)*reverse > 0);--j)
                arr[j+1] = arr[j];
            arr[j+1] = temp;
        }
    }

    /* B U B B L E  -  S O R T */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr){
        bubbleSort(arr,false);
    }

    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr, boolean descending){
        boolean flag = true;
        int reverse = descending ? -1:1;
        int N = arr.length;
        while(flag){
            flag = false;
            for(int i=0; i<N-1; ++i){
                if(arr[i].compareTo(arr[i+1])*reverse > 0){
                    T temp = arr[i+1];
                    arr[i+1] = arr[i];
                    arr[i] = temp;
                    flag = true;
                }
            }
            --N;
        }
    }

    public static <T> void bubbleSort(T[] arr, Comparator<T> comp){
        bubbleSort(arr,comp,false);
    }

    public static <T> void bubbleSort(T[] arr, Comparator<T> comp,boolean descending){
        boolean flag = true;
        int reverse = descending ? -1:1;
        int N = arr.length;
        while(flag){
            flag = false;
            for(int i=0; i<N-1; ++i){
                if(comp.compare(arr[i],arr[i+1])*reverse > 0){
                    T temp = arr[i+1];
                    arr[i+1] = arr[i];
                    arr[i] = temp;
                    flag = true;
                }
            }
            --N;
        }
    }

    /* H E A P - S O R T */
    private static <T> void makeHeap(T[] arr,Comparator<T> comp){
        // n-1 is the last leaf, the formula for finding the parent of a given leaf is:
        // (i-1)/2. so if this is the last parent we'll have all the leafs from lastParent+1,...,n-1;
        int lastParent = (arr.length-2)/2;
        for(int i=lastParent;i>=0; --i)
            fixHeap(arr,arr.length,i,comp);
    }

    private static <T> void popHeap(T[] heap, int heapSize,Comparator<T> comp){
        T temp = heap[0];
        heap[0] = heap[heapSize-1];
        heap[heapSize-1] = temp;
        fixHeap(heap, heapSize-1, 0,comp);
    }

    private static <T> void fixHeap(T[] heap,int heapSize, int root,Comparator<T> comp){
        int max  = -1;
        int leftChild = 2*root+1; // GET LEFT CHILD
        int rightChild = 2*root+2; // GET RIGHT CHILD
        if(leftChild<heapSize && rightChild<heapSize){ // if both are valid get the max
            max = comp.compare(heap[leftChild],heap[rightChild]) > 0 ? leftChild:rightChild;
        }
        else if(leftChild<heapSize){
            max = leftChild;
        }
        else if(rightChild<heapSize){
            max = rightChild;
        }
        else
            return;
        
        if(comp.compare(heap[max],heap[root]) > 0){
            T temp = heap[max];
            heap[max] = heap[root];
            heap[root] = temp;
            fixHeap(heap, heapSize, max,comp);
        }
    }

    public static <T extends Comparable<? super T>> void heapSort(T[] arr){
        heapSort(arr,false);
    }

    public static <T extends Comparable<? super T>> void heapSort(T[] arr,boolean descending){
        int reverse = descending ? -1:1;
        Comparator<T> comp = (a,b) -> a.compareTo(b)*reverse;
        makeHeap(arr,comp);
        int heapSize = arr.length;
        while(heapSize != 0){
            popHeap(arr,heapSize,comp);
            --heapSize;
        }
    }

    public static <T> void heapSort(T[] arr, Comparator<T> comp){
        heapSort(arr,comp,false);
    } 

    public static <T> void heapSort(T[] arr, Comparator<T> comp,boolean descending){
        int reverse = descending ? -1:1;
        Comparator<T> compReverse = (a,b) -> comp.compare(a,b)*reverse;
        makeHeap(arr,compReverse);
        int heapSize = arr.length;
        while(heapSize != 0){
            popHeap(arr,heapSize,compReverse);
            --heapSize;
        }
    } 
    
    /* M E R G E - S O R T */
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
