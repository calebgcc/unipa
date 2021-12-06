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
}
