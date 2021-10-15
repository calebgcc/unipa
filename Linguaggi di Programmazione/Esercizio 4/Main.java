import java.util.*;

public class Main {

    public static void stampaArray(int[] array){
        System.out.print("[");
        int i=0;
        for(i=0; i<array.length-1; ++i){
            System.out.print(array[i]+", ");
        }
        System.out.println(array[i]+"]");
    }

    public static double avg(int[] array){
        int sum = 0;
        for(int a : array)
            sum += a;
        return sum/array.length;
    }

    public static int[] selezionaPari(int[] array){
        int c=0, temp;        
        for(int i=0; i<array.length; ++i){
            if(array[i]%2 == 0){
                temp = array[i];
                array[i] = array[c];
                array[c] = temp;
                ++c;
            }
        }

        int[] ans = new int[c];
        for(int i=0; i<c; ++i){
            ans[i] = array[i];
        }

        return ans;
    }

    public static int[] eliminaDuplicati(int[] array){

        Arrays.sort(array);
        int c=1, temp;
        for(int i=1; i<array.length; ++i){
            if(array[i] != array[c-1]){
                temp =array[i];
                array[i] = array[c];
                array[c] = temp;
                ++c; 
            }
        }

        int[] vet = new int[c];
        for(int i=0; i<c; ++i){
            vet[i] = array[i];
        }

        return vet;
    }


    public static void main(String[] args){
        
        int[] vet = {1,3,2,4,4,6,7,8,5,6,9,0,0,1};
        
        stampaArray(vet);
        System.out.println("avg:: "+avg(vet));

        stampaArray(selezionaPari(vet));
        
        stampaArray(eliminaDuplicati(vet));

    }
}
