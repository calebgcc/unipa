import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
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

    //                       *  H A S H S E T  *
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


    public static void main(String[] args){
        testHashSet();
        testLinkedHashSet();
        testTreeSet();
    }
}
