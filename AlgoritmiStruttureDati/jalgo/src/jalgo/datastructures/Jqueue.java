package jalgo.datastructures;

import java.util.Iterator;
import java.util.List;

public class Jqueue<T> implements Iterable<T>{

    Jlist<T> queue;

    public Jqueue(){
        queue = new Jlist<>();
    }

    public Jqueue(List<T> list){
        queue = new Jlist<>();
        for(T value : list)
            this.push(value);
    }
    
    public Jqueue(T[] arr){
        queue = new Jlist<>();
        for(T value : arr)
            this.push(value);
    }

    public void push(T value){ // enqueue
        queue.append(value);
    }
    
    public T pop(){ // dequeue
        T temp = queue.get(0);
        queue.remove(0);
        return temp;
    }

    public T top(){
        return queue.get(0);
    }

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    @Override
    public String toString(){
        return queue.toString();
    }
    
    @Override
    public Iterator<T> iterator() {
        return null;
    }
    
}