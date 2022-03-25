package jalgo;

import jalgo.datastructures.Jstack;

public class Main{
    
    public static void main(String[] args){
        Jstack<Integer> stack = new Jstack<>();
        for(int i=0; i<10; ++i){
            stack.push(i);
            System.out.println(stack.pop());
        }
        System.out.println(stack);
    }
}