package jalgo.datastructures;

import java.util.Iterator;
import java.util.List;

public class Jstack<T> implements Iterable<T> {

    private Jlist<T> stack;

    public Jstack(){
        stack = new Jlist<>();
    }

    public Jstack(List<T> list){
        for(T value : list)
            this.push(value);
    }

    public Jstack(T[] arr){
        for(T value : arr)
            this.push(value);
    }

    public void push(T value){
        if(stack.isEmpty())
            stack.append(value);
        else
            stack.insert(0, value);
    }

    public T pop(){
        T temp = stack.get(0);
        stack.remove(0);
        return temp;
    }

    public T top(){
        return stack.get(0);
    }

    public int size(){
        return stack.size();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }


    @Override
    public String toString(){
        return stack.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return stack.iterator();
    }
    
}
