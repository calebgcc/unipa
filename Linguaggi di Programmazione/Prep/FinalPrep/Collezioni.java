import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

// Una Struttura Dati Astratti (SDA) è una struttura dati definita 
// solamente dal `cosa fa` invece di `come lo fa`
// ( per questo in Java vengono usate le interfacce )

// Una implementazione di una SDA viende definita Struttura Dati Concreta
// in questo caso il focus è su `come lo fa`
// Es. List (SDA) => { ArrayList (SDC) , LinkedList (SDC) }

/*

                                     Collections
                    |_____________________|_____________________|
                  *Set                  *List                *Map
              _______|_______             |                     |
             |               |            |_ LinkedList         |_ HashMap 
          HashSet       *SortedSet        |                     |
                             |            |                     |
                          TreeSet         |_ ArrayList          |_ *SortedMap
                                                                        |
                                                                        |_ TreeMap

 [!] esiste anche *Queue di cui tecnicamente fa parte anche LinkedList

*/

// Collection<E> è l'interfaccia che si occupa definire una generica SDA
// I metodi si dividono in:
// [Basic] Operazioni di base (inserimento, cancellazione, ricerca)
// [Bulk] Operazioni su intere collezioni (inserimento, cancellazione, ricerca) di collezioni
// [Array] Operazioni per trasformare le collezioni in array
// [Optional] Operazioni che se non supportate lanciano UnsupportedOperationException (sottoclasse di RuntimeException)

// I metodi contains() & remove() prendono a prametro un tipo Object
// invece che un genrics; Questo perchè al loro interno utilizzano equals()

class Inutile{
    public Integer a;
    public Inutile(int a){this.a = a;}
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass()!=o.getClass()) return false;
        Inutile i = (Inutile) o;
        return this.a == i.a;
    }
    //@Override
    //public int hashCode() { return 1;}
    @Override
    public String toString() {return String.valueOf(this.a);}
}


public class Collezioni {

    //                             *  S E T  *
    // Gli HashSet possono prendere a costruttutore i seguenti valori
    // (int capacity) - (int capacity, float loadFactor) - (Collection<? extends E> c)
    // la size di un hashset restituisce il numero di elementi che possiede
    // la capacity indica quanti ne può avere al massimo
    // ogni volta che size/capacity supera il loadFactor l'hashset alloca nuova memoria.
    public static void testHashSet(){ // HashSet utilizza equals per controllare che l'elementi sia unico
        Inutile uno = new Inutile(1); // in questo caso tutti gli oggetti Inutile hanno hashCode = 1
        Inutile due = new Inutile(2); // l'hashset aggiunge comunque entrambi perchè sono diversi per equals
        HashSet<Inutile> h = new HashSet<>(); // gli elementi saranno inseriti tutti nello stesso bucket
        System.out.println(h.add(uno) + " " + h.add(due)); // sarà quindi più lenta la ricerca.
    }
    public static void testLinkedHashSet(){ // hashset salva gli oggetti in bucket in base al loro hashCode
        HashSet<Inutile> h = new HashSet<>(); // in questo caso ho commentato l'override di hashCode
        LinkedHashSet<Inutile> l = new LinkedHashSet<>(); // gli elementi non vengono stampi nel loro
        h.add(new Inutile(1)); h.add(new Inutile(2)); h.add(new Inutile(3)); // ordine di inserimento.
        l.add(new Inutile(1)); l.add(new Inutile(2)); l.add(new Inutile(3)); // con LinkedHashSet
        for(Inutile i : h) // risolviamo questo problema
            System.out.print(i+","); // l'implementazione interna è tale che possiamo
        for(Inutile i : l) // essere certi che la stamp viene effettuata seguendo l'ordine di inserimento
            System.out.print(" ;"+i);
        System.out.println("");
    }
    // L'interfaccia *SotedSet mette a disposizione delle operazioni per set ordinati
    // first() - last() // per prendere il primo e ultimo elemento
    // subSet(start,end) // un intervallo [start,end) utilizzando come argomenti gli elementi
    // headset(E) - tailSet(E) // elementi minori - maggiori/uguali di E
    public static void testTreeSet(){ // Gli elementi E di un treeset devono estendere
        SortedSet<Inutile> s = new TreeSet<>(new Comparator<Inutile>() { // Comparable<? super E>
            @Override
            public int compare(Inutile x, Inutile y){
                return x.a.compareTo(y.a);
            }
        }); // oppure bisogna passare un comparator al costruttutore
        s.add(new Inutile(1)); s.add(new Inutile(3));
        s.add(new Inutile(4)); s.add(new Inutile(2)); s.add(new Inutile(5));
        System.out.print("heaedset"+s.headSet(new Inutile(3))+ " ");
        System.out.println("tailset"+s.tailSet(new Inutile(3)));
    } 
    // È spesso utile avere una vista immutabile di un insieme, questi metodi statici sono utili
    // Collections.unmodifiableSet(Set<E> s) - Set.of(E... e) - Set.copyOf(Collections<E> c)
    
    // Parliamo di ~ I T E R A T O R I ~
    // L'interfaccia *Iterable<T> è una sovrainterfaccia di Collection
    // contiene un solo metodo Iterator<T> iterator()
    // per implementare il metodo iterator() bisogna creare un oggetto che
    // implementi l'interfaccia *Iterator<E>.
    // *Iterator contiene i metodi next() - hasNext() - remove() [optional]
    // si può usare il for esteso su ogni classe che implementa *Iterator
    // [!] Quando bisogna rimuovere un elemento da una collezione, non usare il for esteso,
    // usoa un iteratore manualmente.
    public static void testIterator(){
        Set<Character> set = new HashSet<>();
        set.add('c'); set.add('a'); set.add('l'); set.add('e'); set.add('b');
        Iterator<Character> i = set.iterator();
        while(i.hasNext())
            System.out.print(i.next()+" ");
        System.out.println("");
    }

    //                             *  L I S T  *
    // *List è una sottointerfaccia di Collection, implementa diversi nuovi metodi
    // [] add(int index,E element) per inserire un elemento in una specifica posizione
    // [] get(int index) per richiedere l'elemento alla i-esima posizione
    // [] indexOf(Object o) restituisce il primo indice in cui si trova l'elemento, -1 se non c'è (esiste lastIndexOf())
    // [] set(int index,E element) modifica un elemento ad una data posizione
    // [] subList(int start,int end)
    // Le liste hanno anche la particolarità di avere i listIterator() - listIterator(int index)
    // I listIterator pssono andare avanti e indietro.
    public static void testListIterator(){ // i listIterator partano dall'elemento i-esimo
        List<Integer> list = new LinkedList<>(); // quando si esegue il next()
        list.add(1); list.add(2); list.add(3); // e dall'elemento i-1 quando si esgue un previous()
        ListIterator<Integer> it = list.listIterator(2); // ricorda che puoi eseguire remove() e set() 
        while(it.hasPrevious()) // solo dopo avere richiamato un next() o un previous
            System.out.print(it.previous()+", "); // stamperà 2, 1,
        it = list.listIterator(0);
        while(it.hasNext()){ // stamperà .1 .2 .3
            System.out.print(" ."+it.next()); 
            it.set(7);
            it.add(3);
        }
        System.out.println(" "+list); // stampa [7,3,7,3,7,3]
    }
    // Gli ArrayList<E> sono un implementazione di *List
    // come gli HashSet hanno una capacity (default 10) e un size;
    // LinkedList<E> è un implementazione di *List
    // altri ai metodi di list aggiunge:
    // addFirst() - addLast()
    // getFirst() - getLast()
    // removeFirst() - removeLast()
    // Se vogliamo possiamo avere una vista immutabile con:
    // Collections.unmodifiableList(Collection<T> c) - List.of(E... e) - List.copyOf(List<E> l)
    public static void testEliminaPari(){
        List<Integer> list = new LinkedList<>();
        list.add(1); list.add(2); list.add(4); list.add(7); list.add(8);
        ListIterator<Integer> it = list.listIterator();
        while(it.hasNext()){
            if(it.next()%2==0)
                it.remove();
        }
        System.out.println(list);
    }

    //                             *  M A P  *
    // I diozionari, array-associativi o mappe sono una struttura dati astratta
    // che rappresentano un'associazione tra un coppia chiave-valore;
    // L'interfaccia Map<K,V> mette a disposizione le classiche operazione di un dizionario
    // [] get(Object key)
    // [] put(K key, V value)
    // [] remove(K key) *optional
    // [] containsKey(Object key) / conainsValue(Object value)
    // [] putAll(Map<? extends K,? extends V> m)
    // [] keySet() / entrySet() / values()
    // Map.Entry<K,V> equivale al concetto C++ di pair
    // permette di getKey() - getValue() - setValue() *optional
    // 
    // HashMap è un implementazione di *Map
    // Di default ha una capacity 16 e un loadFactor di 0.75
    // Come per HashSet esiste LinkedHashMap che permette di iterare seguendo l'ordine di inserimento
    // L'interfaccia SortedMap<K,V> rappresenta un SDA in cui le chiavi sono ordinate
    // vi sono operazioni aggiunte come 
    // [] firstKey() / lastKey()
    // [] headMap(K key) / tailMap(K key)
    // [] subMap(K start, K end)
    // TreeMap è un implementazione di *SortedMap
    // le chiavi devono implementare Comparable o deve essere passato un comparatore al costruttore

    public static void main(String[] args){
        testHashSet();
        testLinkedHashSet();
        testTreeSet();
        testIterator();
        testListIterator();
        testEliminaPari();
    }
}
