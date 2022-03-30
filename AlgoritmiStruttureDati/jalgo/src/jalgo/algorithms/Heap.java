package jalgo.algorithms;

import java.util.Comparator;

public class Heap {
    
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

    public static <T extends Comparable<? super T>> void sort(T[] arr){
        sort(arr,false);
    }

    public static <T extends Comparable<? super T>> void sort(T[] arr,boolean descending){
        int reverse = descending ? -1:1;
        heapSort(arr,(a,b)->a.compareTo(b)*reverse); 
    }

    public static <T> void sort(T[] arr, Comparator<T> comp){
        sort(arr,comp,false);
    } 

    public static <T> void sort(T[] arr, Comparator<T> comp,boolean descending){
        int reverse = descending ? -1:1;
        heapSort(arr,(a,b)->comp.compare(a, b)*reverse); 
    }
    
    private static <T> void heapSort(T[] arr, Comparator<T> comp){
        makeHeap(arr,comp);
        int heapSize = arr.length;
        while(heapSize != 0){
            popHeap(arr,heapSize,comp);
            --heapSize;
        }    
    }
}
