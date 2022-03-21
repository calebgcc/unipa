package jalgo;

import jalgo.datastructures.Jlist;

public class Main{
    public static void main(String[] args){ 

        Integer a[] = {6,5,4,3,2,1,0};
        Jlist<Integer> list = new Jlist<>(a);
        System.out.println(list);
        
    }
}