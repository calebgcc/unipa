package jalgo.datastructures;

public class Jdictionary<K,E> {
    
    public class Jpair{
        public K key;
        public E value;
        public Jpair(K k, E e){
            key=k;
            value=e;
        }
        @Override 
        public String toString(){return "("+key+","+value+")";}
    } 
    
    private Jlist<Jpair>[] table;
    private int size = 0; // number of elements stored in the table
    private int capacity = 3; // capacity of the table
    private double loadFactor = 0.75; // when size/capacity becomes greater then loadFactor it's time to double capacity


    @SuppressWarnings("unchecked")
    public Jdictionary(){
        this.table = new Jlist[this.capacity];
        for(int i=0; i<this.capacity; ++i)
            this.table[i] = new Jlist<Jpair>();
    }

    @SuppressWarnings("unchecked")
    public Jdictionary(int capacity){
        this.capacity = capacity;
        this.table = new Jlist[this.capacity];
        for(int i=0; i<this.capacity; ++i)
            this.table[i] = new Jlist<Jpair>();
    }

    private int h(K key){
        return Math.abs(key.hashCode() % capacity);
    }

    public E get(K k){
        for(Jpair p : table[h(k)]){
            if(p.key.equals(k))
                return p.value;
        }
        return null;
    }

    public void put(K k, E e){
        int len = table[h(k)].size();
        for(int i=0; i<len; ++i){
            if(table[h(k)].get(i).key.equals(k)){
                table[h(k)].get(i).value = e;
                return;
            }
        }
        // if the element it's not present we insert
        if(((size+1)/capacity) > loadFactor)
            this.resize();
        table[h(k)].append(new Jpair(k, e));
        this.size++;
    }

    public void delete(K k){
        int len = table[h(k)].size();
        for(int i=0; i<len; ++i){
            if(table[h(k)].get(i).key.equals(k)){
                table[h(k)].remove(i);
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(){
        Jlist<Jpair> list = new Jlist<>();
        for(Jlist<Jpair> l : table){
            for(Jpair p : l){
                list.append(p);
            }
        }

        capacity *= 2;
        table = new Jlist[capacity];
        for(int i=0; i<this.capacity; ++i)
            this.table[i] = new Jlist<Jpair>();
        for(Jpair p : list){
            this.put(p.key,p.value);
        }
    }

    // TODO da togliere
    public void log(){
        System.out.println("***********");
        for(Jlist<Jpair> l : table){
            System.out.println(l);
        }
        System.out.println("***********");
    }


    @Override
    public String toString(){
        return "size: "+this.size+" capacity: "+this.capacity;
    }
}
