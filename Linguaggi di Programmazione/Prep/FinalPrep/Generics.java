import java.util.ArrayList;
import java.util.IllegalFormatWidthException;

import javax.swing.text.StyledEditorKit.BoldAction;

// Esercitazione Generics

class Stack<T>{  // test per i generics di classe
    private ArrayList<T> stack;
    
    public Stack(){
        stack = new ArrayList<>();
    }

    public void push(T t){
        stack.add(t);
    }

    public T pop(){ // non vuole le parentesti public <T> T pop() // utilizza il T dello stack di default
        if(stack.size() == 0)
            return null;
        T t = stack.get(stack.size()-1);
        stack.remove(stack.size()-1);
        return t;
    }

    public static void test(){
        Stack<Character> pila = new Stack<>();
        pila.push('a'); pila.push('b'); pila.push('c');
        Character ch;
        while((ch = pila.pop())!=null)
            System.out.print(ch);
        System.out.println("");
    }

}


class Cane implements Comparable<Cane>{
    private String razza;
    public Cane(String razza){ this.razza = razza; }
    public String getRazza() {return this.razza;}
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass()!=o.getClass()) return false;
        Cane c = (Cane) o;
        return this.razza.equals(c.getRazza());
    }
    @Override
    public int hashCode(){
        return this.razza.hashCode();
    }
    @Override
    public String toString(){
        return this.razza;
    }
    @Override
    public int compareTo(Cane o){
        if(this.equals(o)) return 0;
        return this.getRazza().compareTo(o.getRazza());
    }
}

public class Generics{
    // <? super T> indica T o una superclasse di T
    // <? extends T> indica T o una classe che estende T
    public static <T extends Comparable<? super T>> T max(T a, T b){
        return a.compareTo(b)>0 ? a:b;
    }

    public static void main(String[] args){
        Stack.test();

        System.out.println(max(1,2));
        System.out.println(max('a','0'));
        System.out.println(max(new Cane("bulldog"),new Cane("labrador")));
    }
}