package jalgo;

import java.util.List;

import jalgo.datastructures.Jqueue;
import jalgo.datastructures.Jstack;

public class Main{
    
    public static void main(String[] args){
        Jqueue<String> coda = new Jqueue<>(List.of("a","b","c","d"));
        Jstack<Integer> stack = new Jstack<>(new Integer[]{1,2,3,4,5,6,7,8,9,10}); 

        System.out.println(stack);
        System.out.println(coda);

        while(!stack.isEmpty())
            System.out.print(stack.pop()+" ");

        System.out.println();

        while(!coda.isEmpty())
            System.out.print(coda.pop()+" ");

        System.out.println();
    }
}