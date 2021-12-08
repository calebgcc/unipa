package main;


import java.util.*;
import java.util.stream.*;
import java.io.*;

// le classi che estendono Collections da Java 8 hanno il metodo stream()
// per gli array si può usare la mixinclass Arrays.stream(array)

public class EsempioStream{
    
    // Esempio per forEach()
    public static void esForEach(){
        List<String> list = List.of("punto","&","virgola");
        list.stream()
        .forEach(s -> System.out.print(s)); // in alternativa .forEach(System.out::print) // method reference    
        System.out.println("");
    }

    // esempio con tokens
    public static void esTokens(){
        try{
            Scanner scan = new Scanner(new File("main/Main.java"));
            Stream<String> s = scan.useDelimiter("\\s|\\W").tokens(); // come se fosse uno split, ma ritorna uno stream

            s.forEach(System.out::println);

            s.close();
            scan.close();
        }catch(Exception e){}
    }

    // esempio IntStream, LongStream, DoubleStream
    public static void esPrimitiveStream(){ 
        // conversione a stream di long (inutile a solo scopo informativo) / esiste anche asDoubleStram()
       IntStream.range(0,10).asLongStream().forEach(d -> System.out.print(d+" # ")); 
       System.out.println("");
    }

    // esempio Random Stream
    public static void esRandom(){
        // random interi tra 1 e 7 escluso con distribuzione uniforme
        new Random().ints(1,7).limit(10).forEach(i -> System.out.print(i + " * "));
        System.out.println("");
    }


    // esempio con filter
    public static void esFilter(String ...vet){
        Arrays.stream(vet)
        .filter(s -> s.length()>2)
        .forEach(s -> System.out.print(s + " - "));
        System.out.println("");
    }

     // esempio con map
     // mapToInt mapToDouble mapToLong se map dovesse ritornare tipi primitivi
     public static void esMap(String ...vet){
        Arrays.stream(vet)
        .map(s -> s.toUpperCase())
        .forEach(s -> System.out.print(s + " : "));
        System.out.println("");
    }

    // es flatMap
    public static void esFlatMap(){
        List<List<Integer>> list = List.of(
            List.of(1,2,3),
            List.of(4,5,6),
            List.of(7,8,9),
            List.of(0)
        );

        list.stream()
        .flatMap(l -> l.stream())
        .forEach(i -> System.out.print(i + " . "));
        System.out.println("");
    }

    // es limit and skip
    // Stream.iterate crea uno stream infinito
    public static void esLimitSkip(){
        Stream.iterate(1, i -> i*2)
        .skip(10)
        .limit(5)
        .forEach(i -> System.out.print(i + " ° "));
        System.out.println("");
    }

    // es distinct utilizza equals
    public static void esDistinct(String ...vet){
        Arrays.stream(vet)
        .distinct()
        .forEach(i -> System.out.print(i + " | "));
        System.out.println("");
    }

    // Il metodo takeWhile restituisce lo stream ottenuto fino a quando è verificata la condizione
    // Il metodo dropWhile restituisce lo stream ottenuto a partire da quando non è più verificata la condizione
    // es takeWhile
    public static void esWhile(){
        Stream.iterate(0,i -> i+2)
        .takeWhile(i -> i < 12)
        .dropWhile(i -> i < 6)
        .forEach(i -> System.out.print(i + " = "));
        System.out.println("");
    }

    // es sorted
    public static void esSorted(String ...vet){
        Arrays.stream(vet)
        .sorted((a,b) -> a.length()-b.length())
        .forEach(s -> System.out.print(s + " "));
        System.out.println("");
    }

    // es peek e toArray()
    public static void esPeek(){
        Integer[] vet = Stream.of("a","ab","abba","abbabba","abbaa","aaabbb")
        .filter(s -> {
            int a = 0, b = 0;
            for(int i=0; i<s.length(); ++i){
                if(s.charAt(i)=='a') ++a;
                else ++b;
            }
            return a==b;
        })
        .peek(s -> System.out.print(s + " * "))
        .map(s -> s.length())
        .sorted((a,b) -> a.compareTo(b)*(-1))
        .toArray(Integer[]::new); // terminale
        System.out.println(Arrays.toString(vet));
    }

    // es Optional e max/min
    public static void esOptional(String ...vet){
        Optional<String> longest = Arrays.stream(vet).max(Comparator.comparing(String::length));
        longest.ifPresent(s -> System.out.print(s + " - "));
        Optional<String> smallest = Arrays.stream(vet).min(Comparator.comparing(String::length));
        smallest.ifPresent(s -> System.out.println(s));
    }

    // es anyMatch noneMatch allMatch
    public static void esMatch(){
        System.out.print(Stream.of(1,2,4,8,16,32).allMatch(n->n%2==0)+" ");
        System.out.print(Stream.of(1,2,4,8,16,32).anyMatch(n->n%2==0)+" ");
        System.out.println(Stream.of(1,2,4,8,16,32).noneMatch(n->n%2==0));
    }

    // es findFirst / findAny
    // restituiscono il primo (rispettivamente uno qualunque) sotto forma di Optional
    public static void esFind(){
        Optional<Integer> g = Stream.of(2,6,4,3,1,0,9)
        .filter(i -> i%2==1)
        .sorted((a,b) -> a.compareTo(b))
        .findAny(); // .findFirst();
        if(g.isPresent())
            System.out.println("find:: "+g.get());
    }

    // es reduce
    public static void esReduce(){
        int i = Stream.of(1,2,3)
                .reduce(0,(a,b)->a+b); // lo zero raprresentà l'identity value nel caso lo stream fosse vuote
        System.out.println("reduce:: "+i); // se non si inserisci l'identity lo stream restituisce un Optional
    }

    // es summaryStatistics
    public static void esStatistics(){
        System.out.println(
            Stream.of(1,2,3)
            .mapToInt(i -> (int)i)
            .summaryStatistics() // funziona per stream di interi, long e double
            // getSum() getMax() getMin() getCount()
        );  // se aggiungi .get... ottieni solo la statistica che ti interessa
    }


    // es collect
    public static void esCollect(){
        // Collect<T,A,R> Tipo, Accumulatore,Risultato
        Set<String> s = Stream.of("ciao","ciao","collect",":)").collect(Collectors.toSet());
        // esistono anche toList(), toMap(), toCollection()

        // toList() e toSet() non hanno parametri
        // toCollection() prende a parametro un supplier
        List<Integer> l = Stream.of(1,1,2,3,5).collect(Collectors.toCollection(LinkedList::new));

        // toMap() prende due parametri, una funzione per creare le chiavi, e una per i valori
        Map<String,Integer> m1 = Stream.of("a","a","aa","aaa")
                                .distinct()
                                .collect(
                                    Collectors.toMap(
                                        w->w, 
                                        w->w.length()
                                    )
                                );
        // per toMap() vi sono anche due overloading
        // il primo prende a parametro una terza funzioni che descrive cosa fare con i duplicati
        Map<String,Integer> m2 = Stream.of("a","a","aa","aaa")
                                .collect(
                                    Collectors.toMap(
                                        w->w, 
                                        w->w.length(),
                                        (e,r) -> r // esistente/rimpiazzo
                                    )
                                );
        // l'altro overloading aggiunge una funzione per esistente/rimpazio ed in più un supplier

        // inoltre prima di .collect() dovremo aggiugnere un .boxed() se lo stream è di primitivi
        // esistono anche i metodi toUnmodifiableMap()/Set()/List()
        System.out.println(m2);
    }


    // es partitioningBy
    public static void esPartitioning(){
        // partiziona uno stream in una mappa Map<Boolean, List<T>>
        // dato un predicato crea due liste, una per cui è false, una per cui true
        Map<Boolean,List<Integer>> a = Stream.of(1,2,3,4,5,6,7,8,9,0)
                                       .collect(Collectors.partitioningBy(s -> s%2==0));

        // spesso counting viene usato insieme a partitioning, che ritorna quanti rientrano in una categoria   
        Map<Boolean,Long> b = Stream.of(1,2,3,4,5,6,7,8,9,0)
                            .collect(Collectors.partitioningBy(s -> s%2==0,Collectors.counting()));
        System.out.println(b);
    }

    // es groupingBy
    public static void esGrouping(){
        // ragruppa per chiavi, la creazione delle chiavi è data da una funzione
        Map<Integer,List<String>> m1 = Stream.of("ciao","miao","cacao","un","il")
                                      .collect(Collectors.groupingBy(
                                          s -> s.length()
                                      ));

        // possiamo definire il tipo di Collezione conterrà i valori passando un Collettore
        Map<Integer,TreeSet<String>> m2 = Stream.of("ciao","miao","cacao","un","il")
                                      .collect(Collectors.groupingBy(
                                          s -> s.length(),
                                          Collectors.toCollection(TreeSet::new)
                                      ));

        // o ancora possiamo passare un Supplier per definire una mappa specifica
        TreeMap<Integer,ArrayList<String>> m3 = Stream.of("ciao","miao","cacao","un","il")
                                      .collect(Collectors.groupingBy(
                                          s -> s.length(),
                                          TreeMap::new,
                                          Collectors.toCollection(ArrayList::new)
                                      ));
        System.out.println(m3);
    }

}
