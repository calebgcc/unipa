package jalgo.datastructures;

import java.util.List;

public class Jlist<T> {

    // class node for Jlist
    private class Node{
        public T value;
        public Node next;
        public Node prev;
        Node(T value){
            this.value = value;
            this.next = this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private Node iterator;
    private int size;

    public Jlist(){
        this.head = this.tail = this.iterator = null;
        this.size = 0;
    }

    public Jlist(List<T> list){
        this.head = this.tail = this.iterator = null;
        this.size = 0;

        for(T value : list)
            this.append(value);
    }

    public Jlist(T[] array){
        this.head = this.tail = this.iterator = null;
        this.size = 0;

        for(T value : array)
            this.append(value);
    }

    public void set(int index) throws IndexOutOfBoundsException{
        if(index>=size || index<0)
            throw new IndexOutOfBoundsException();
        
        Node temp;
        if(index > (size/2)){
            temp = tail;
            for(int i=size-1;i>index;--i)
                temp = temp.prev;
            iterator = temp;
        }
        else{
            temp = head;
            for(int i=0;i<index;++i)
                    temp = temp.next;
            iterator = temp;
        }
    }

    public boolean hasNext(){
        return iterator==null ? false:(iterator.next != null);
    }

    public boolean hasPrev(){
        return iterator==null ? false:(iterator.prev != null);
    }

    public boolean isSet(){
        return iterator!=null;
    }

    public void next() throws RuntimeException{
        if(iterator == null)
            throw new RuntimeException();
        iterator = iterator.next;
    }

    public void prev() throws RuntimeException{
        if(iterator == null)
            throw new RuntimeException();
        iterator = iterator.prev;
    }

    public T get() throws RuntimeException{
        if(iterator == null)
            throw new RuntimeException();
        return iterator.value;
    }

    public void remove() throws RuntimeException{
        if(iterator == null)
            throw new RuntimeException();
        size--;
        if(iterator == head){
            head = head.next;
            if(head != null)
                head.prev = null;
            iterator = head;
        }
        else if(iterator == tail){
            tail = tail.prev;
            tail.next = null;
            iterator = null;
        }
        else{
            (iterator.prev).next = iterator.next;
            (iterator.next).prev = iterator.prev;
            iterator = iterator.next;
        }
    }

    public void insert(T value){
        if(iterator == null)
            throw new RuntimeException();
        ++size;
        if(iterator == tail){
            tail.next = new Node(value);
            (tail.next).prev = tail;
            tail = tail.next;
            tail.next = null;
        }
        else{
            Node temp = new Node(value);
            temp.prev = iterator;
            temp.next = iterator.next;
            (iterator.next).prev = temp;
            iterator.next = temp;
        }
    }


    public void append(T value){
        if(head == null){
            head = new Node(value);
            head.next = head.prev = null;
            tail = head;
            size++;
            return;
        }
        Node temp = new Node(value);
        temp.prev = tail;
        temp.next = null;
        tail.next = temp;
        tail = temp;
        size++;
    }

    // inserisce un nuovo nodo primo del nodo a posizione index
    public void insert(int index,T value) throws IndexOutOfBoundsException{
        if(index>=size || index<0)
            throw new IndexOutOfBoundsException();

        Node temp;
        if(index == 0){
            size++;
            temp = new Node(value);
            temp.next = head;
            head.prev = temp;
            head = temp;
            return;
        }
        else if(index >= (size/2)){
            temp = tail;
            for(int i=size-1; i>index; --i)
                temp = temp.prev;
        }
        else{
            temp = head;
            for(int i=0; i<index; ++i)
                temp = temp.next;
        }
        // 1>3 1<3
        size++;
        Node novo = new Node(value); // 1>3 1<3 , 2 
        (temp.prev).next = novo; // 1>2 1<3
        novo.prev = temp.prev; // 1>2 1<2 1<3
        novo.next = temp;  // 1>2 1<2 2>3 1<3
        temp.prev = novo;// 1>2 1<2 2>3 2<3
    }

    public void remove(int index) throws IndexOutOfBoundsException{
        if(index>=size || index<0)
            throw new IndexOutOfBoundsException();

        if(index == 0){
            size--;
            iterator = iterator==head ? null:iterator;
            head = head.next;
            if(head != null) // if the head was the only node
                head.prev = null;
            return;
        }
        else if(index == (size-1)){
            size--;
            iterator = iterator==tail ? null:iterator;
            tail = tail.prev;
            tail.next = null;
            return;
        }
        
        Node temp;
        if(index >= (size/2)){
            temp = tail;
            for(int i=size-1; i>index; --i)
                temp = temp.prev;
        }
        else{
            temp = head;
            for(int i=0; i<index; ++i)
                temp = temp.next;
        }    
        size--;
        iterator = iterator==temp ? null:iterator;
        (temp.prev).next = temp.next; // next of the previous is equal to temp.next
        (temp.next).prev = temp.prev; // previous of the next is equal to temp.prev
    }

    public T get(int index)throws IndexOutOfBoundsException{
        if(index>=size || index<0)
            throw new IndexOutOfBoundsException();
        
        if(index > (size/2)){
            Node temp = tail;
            for(int i=size-1;i>index;--i)
                temp = temp.prev;
            return temp.value;
        }
    
        Node temp = head;
        for(int i=0;i<index;++i)
                temp = temp.next;
        return temp.value;
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

}
