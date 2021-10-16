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

    public static void eliminaPari(ArrayList<Integer> al){
        for(int i=0; i<al.size();){
            if(al.get(i)%2 == 1){
                al.remove(i);
            }
            else
                ++i;
        }
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

    public static void rimuoviDuplicati(ArrayList<Integer> al){
        Collections.sort(al);
        for(int i=1; i<al.size();){
            if(al.get(i) == al.get(i-1)){
                al.remove(i);
            }
            else
                ++i;
        }
    }


    public static void main(String[] args){
        
        int[] vet = {1,3,2,4,4,6,7,8,5,6,9,0,0,1};
        
        stampaArray(vet);
        System.out.println("avg:: "+avg(vet));

        stampaArray(selezionaPari(vet));
        
        stampaArray(eliminaDuplicati(vet));

        ArrayList<Integer> al = new ArrayList<Integer>();
        for(int i=10; i<=19; ++i)
            al.add(i);
        al.add(13);
        al.add(14);
        al.add(13);
        al.add(12);
        al.add(10);
        
        eliminaPari(al);
        System.out.println(al);
        
        rimuoviDuplicati(al);
        System.out.println(al);
    }
}
