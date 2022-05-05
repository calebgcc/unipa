package jalgo.datastructures;

import java.util.Iterator;
import java.util.List;

public class Jlist<T> implements Iterable<T> {

    // class node for Jlist
    private class Node{
        public T value;
        public Node next;
        Node(T value){
            this.value = value;
        }
    }

    // class for iterator
    private class JlistIterator implements Iterator<T>{        
        private Node iterator;

        public JlistIterator(){
            iterator = head;
        }

        @Override
        public boolean hasNext() {
            return (iterator!=null); //? false:(iterator.next!=null);
        }

        @Override
        public T next() {
            T temp = iterator.value;
            iterator = iterator.next;
            return temp;
        }
        
    }

    private Node head; // head of linked Jlist
    private Node tail; // tail of linked Jlist
    private int size; // number of nodes in Jlist


    public Jlist(){
    }

    public Jlist(List<T> list){
        for(T value : list)
            this.append(value);
    }

    public Jlist(T[] array){
        for(T value : array)
            this.append(value);
    }


    public void append(T value){
        size++;
        if(head == null){
            head = new Node(value);
            head.next = null;
            tail = head;
            return;
        }
        Node temp = new Node(value);
        tail.next = temp;
        tail = temp;
    }

    // insert(index,value) => create new node and insert it before node of index i
    public void insert(int index,T value) throws IndexOutOfBoundsException{
        if(index>=size || index<0)
            throw new IndexOutOfBoundsException();

        Node curr,prev,novo;
        if(index == 0){
            size++;
            novo = new Node(value);
            novo.next = head;
            head = novo;
            return;
        }
        curr = head;
        prev = null;
        for(int i=0; i<index; ++i){
            prev = curr;
            curr = curr.next;
        }
        size++;
        novo = new Node(value);
        prev.next = novo;
        novo.next = curr;
    }

    // remove it's not implemented to be compatible with iterator
    public void remove(int index) throws IndexOutOfBoundsException{
        if(index>=size || index<0)
            throw new IndexOutOfBoundsException();

        Node curr,prev;
        if(index == 0){
            size--;
            head = head.next;
            return;
        }
        
        curr = head;
        prev = null;
        for(int i=0; i<index; ++i){
            prev = curr;
            curr = curr.next;
        }

        if(index == (size-1))
            tail = prev;

        size--;
        prev.next = curr.next;
    }

    public T get(int index)throws IndexOutOfBoundsException{
        if(index>=size || index<0)
            throw new IndexOutOfBoundsException();
    
        Node curr = head;
        for(int i=0;i<index;++i)
            curr = curr.next;
        return curr.value;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }


    @Override
    public String toString(){
        if(size == 0)
            return "[]";
        StringBuilder builder = new StringBuilder("[");
        Node temp = head;
        while(temp.next != null){
            builder.append(temp.value);
            builder.append(", ");
            temp = temp.next;
        }
        builder.append(temp.value);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new JlistIterator();
    }

}
