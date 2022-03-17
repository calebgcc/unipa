package jalgo.datastructures;

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
    private int size;

    public Jlist(){
        this.head = null;
        this.tail = null;
        this.size = 0;
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
