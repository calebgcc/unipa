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
}
