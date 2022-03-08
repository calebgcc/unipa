import java.util.*;

// preparation for the exam

interface Vivente{
    void verso();
}

abstract class Animale implements Vivente{
    private String categoria;
    private String spcie;

    public Animale(String c, String s){
        this.categoria = c;
        this.spcie = s;
    }

    @Override
    public String toString(){
        return "Categoria: "+this.categoria+"\nSpecie: "+this.spcie;
    }

    public final String getCategoria(){return this.categoria;}
    public final String getSpecie(){return this.spcie;}

}

class Cane extends Animale implements Comparable<Cane>{
    private String razza;
    private int eta;
    
    public Cane(String c, String s, String r, int e){
        super(c,s);
        this.razza = r;
        this.eta = e*7;
    }

    @Override
    public String toString(){
        return "Categoria: "+this.getCategoria()+"\nSpecie: "+this.getSpecie()+"\nRazza: "+this.razza+"\nEtà: "+this.eta;
    }

    @Override
    public int hashCode(){
        return 13*this.razza.hashCode()+19*this.eta;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(this.getClass() != o.getClass()) return false;
        Cane c = (Cane) o;
        if(this.razza == c.razza && this.eta == c.eta) return true;
        return false;
    }

    @Override
    public int compareTo(Cane c){
        if(this.equals(c)) return 0;
        if(this.eta > c.eta) return 1;
        return -1;
    }

    @Override
    public void verso(){System.out.println("Wof");}

}


// MAIN CLASS
public class PrimaParte{
    public static void main(String[] args){
       ArrayList<Cane> cani = new ArrayList<>();

       String v = "Bau Bau";
       Cane bob = new Cane("","","dalmata",7){
           @Override
           public void verso(){
               System.out.println(v);
           }
       };

       cani.add(new Cane("","","bulldog",8));
       cani.add(new Cane("","","golden",1));
       cani.add(new Cane("","","golden",2));
       cani.add(new Cane("","","golden",1));
       cani.add(new Cane("","","labrador",7));
       cani.add(bob);

       Collections.sort(cani);
       for(Cane c : cani){
           c.verso();
       }
    }
}



// S T R I N G
/*

    > equals(string) | equalsIgnoreCase(string) [boolean]
        |
        |_compareTo(string) | compareToIgnoreCase(string) [int]  // lessicografico
        |
        |_ startsWith(string) | endsWith(string) [boolean]
        |
        |_ matches(regex) [boolean]
            |
            |_ replaceFirst(regex,string) | replaceAll(regex,string) [string]
            |
            |_ split(regex) [String[]]

    
    > length() [int]

    > charAt(int) [char]

    > indexOf(char/string) | lastIndexOf(char/string)  [int]

    > toLowerCase() | toUpperCase() | strip() [string]

    > substring(int,int) [string] // [start,end)
        |
        |_ contains(string) [boolean]

    > String.valueOf(tipo primitivo) [string]




                                                                                                                                           
*/

// S T R I N G  B U I L D E R
/*


    > append(string) | insert(int,string)

    > reverse()
    
    > delete(int,int) // [start,end)
        |
        |_ deleteCharAt(int)

    > replace(int,int,string) // [start,end)

    > toString() [string]

    > length() | charAt(int) | substrin(int,int) // funzionano come per le stringhe

*/

// W R A P P E R
/*

    > Charcter.isDigit(char) | Character.isLetter(char) [boolean]
    
    > Integer.parseInt(string) [int] // anche per Byte, Short, Long, Float, Double
        |
        |_ Integer.toBinaryString(int) | Integer.toHexString(int) | Integer.toOctalString(int) 

*/

// A R R A Y
/*

    ------------------
    int[] n = new int[3]; 
    int[] n = new int[]{1,2,3}; 
    int[] n = {1,2,3};
    ------------------

    > n.length (attributo)

    > varargs : metodo(int... n){} // con foreach si leggono gli argomenti // puoi passare anche un vettore a parametro

    > Arrays.toString([array]) [string]

    > Arrays.equals([array],[array]) [boolean]

    > Arrays.fill([array],[obj]) | Arrays.fill([array],int,int,[obj]) // [start,end)

    > Array.sort([array]) // inplace

    > Array.binarySearch([array],[obj]) [int]

*/

// A R R A Y   L I S T
/*

   [] Contiene solo riferimenti a oggetti

   > add([obj]) | add(int,[obj])

   > indexOf([obj]) | lastIndexOf([obj]) [int]
        |
        |_ remove(int/[obj])
        |
        |_ contains([obj]) [boolean]

   > size() [int]

   > get(int) [obj] | set(int,[obj])

   > toArray([array])

   > Collections.sort([alist]) | Collections.reverse([alist]) | Collections.shuffle[alist])
   > Collections.max([alist]) | Collections.min([alist]) [obj]
   > Collections.swap([alist],int,int)
   > Collections.binarySearch([alist],[obj]) [int]

*/

// O B J E C T  &  S U B T Y P I N G  &  P O L I M O R F I S M O 
/*

    > Object clone()
    >> deprectato, aiutava a risparmiare memoria

    > boolean equals(Object obj)
    >> equals richiede una relazione di equivalenza (riflessica,simmetrica,transitiva)
    >>> di default il metodo equals implementa la relazione di identità
    |
    |_ il primo controllo solitamente è if(this == obj) return true; 
    |_ il secondo è un controllo di sicurezza if(obj == null) return false;
    |_ controllo sul tipo if(this.getClass() != obj.getClass()) return false;
    |_ poi si può effettuare un cast esplicito e fare ulteriori controlli sugli statu.


    > Class<?> getClass()

    > int hashCode()
    >> viene solitamente riscritta con combinazioni lineari degli attributi con moltiplicatori primi (29a + 31b + 37c)
    >> ricorda di riscrivere sempre hashCode quando riscrivi equals

    > String toString()
    >> di default restituisce this.getClass()+"@"+this.hashCode();


    > Nel subtyping definiamo
    >> Il "dynamic-binding" o "late-binding" ovvero la possibilità di richiamare i metodi della sottoclasse
    da un riferimento ad oggetto della superclasse (l'oggetto referenziato è della sottoclasse) [polimorfismo di metodi virtuali]
        |
        |_ Il controllo sintattico (compiltime) effettuato dalla JVM si chiama "static-type-checking"
        |_ per bypassare questo tipo di controllo si effettua un cast esplicito


    > Il polimorfismo è il principio secondo qui, una stessa espressione può assumere significati diveris
    > Polimorfismo nei dati: Parametri Polimorfi (es. println) , Collezioni Eterogenee (es. arraylist) , Subtyping
    > Polimorfismo nei metodi: Overload , Override , Metodi Virtuali





*/

// A S T R A T T E  &  I N T E R F A C C E
/*

    > I metodi abstract non hanno implementazione
    |
    |_ una classe che contiene almeno un metodo astratto si dirà astratta (public abstract class Scuola{..})
    |   |
    |   |_ una classe astratta non può essere instanziata
    |
    |_ i metodi astratti vanno implementati dalle sottoclassi e verranno detti (metodi concreti)
    |   |
    |   |_ una classe astratta può avere costruttori (non astratti) che verranno usati dalle sottoclassi
    |
    |_ può contenere metodi e attributi static

    DIFFERENZE -- Le classi astratte permettono di definire una gerarchia di ereditarietà (essendo classi)
    >>>>>>>>>> -- Le interfacce impongono l'implementazione di una lista di metodi indipendenti da gerarchie ereditarie 

    > Interfacce
    |
    |_ sono implicitamente (public)
    |   |
    |   |_ i suoi metodi sono implicitamente (public abstract)
    |   |   |
    |   |   |_ si possono definire metodi con corpi  public default void ciao(){System.out.print("ciao");}
    |   |       |
    |   |       |_ nel caso di più metodi default con stessa firma la classe dovrà fare un override del metodo
    |   |          è possibile richiamare i metodi default originali con la sintassi InterfaceName.super.NameDefaultMethod()
    |   |
    |   |_ può contenere anche variabili costanti implicitamente (public static final)
    |
    |_ una classe che implementa un interfaccia deve riscriverne tutti i metodi (o dovrà essere abstract)
    |
    |_ le interfacce fra loro possono derivarsi con extends (possibilità di extends multipli)
        |
        |_ ritorna il diamond-problem, in caso di metdodi duplicati vince sempre la superclasse

    
    > int compareTo(<T> t)
        |
        |_ deve essere compatibile con equals
        |
        |_ deve implemtare una relazione d'ordine totale (riflessiva,antisimmetrica,transitiva)


*/










