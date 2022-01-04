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




public class Collezioni {
    public static void main(String[] args){

    }
}
