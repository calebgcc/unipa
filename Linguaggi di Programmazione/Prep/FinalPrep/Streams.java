import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {


    public static void end(){
        System.out.println("");
    }

    // test 1
    public static void t1(){
        try (Scanner scan = new Scanner(new File("Streams.java"))) {
            // utilizza [^\\w\\d'] tutto ciò che non è caratteri,numeri o apice
            Stream<String> stream = scan.useDelimiter("[^\\w\\d']").tokens();
            Map<String,Integer> map =
                stream.collect(
                    Collectors.toMap(
                        s -> s, // la parola è chiave
                        s -> 1, // ad ogni chiave diamo il valore 1
                        (e,r)->e+1 // ad ogni parola duplicata diamo valore e+1 // valore esistente +1
                    )
                );
            int i = 0;
            for(var pair : map.entrySet()){
                System.out.print("["+pair.getKey()+"] = "+pair.getValue()+" ");
                ++i;
                if(i%3 == 0)
                    System.out.println("");
            }
        } catch (FileNotFoundException e) {
            System.out.println("[!] File non trovato :(");
        }
    }

    // test 2
    public static void t2(){
        Stream.iterate(2, i->i+2)
        .filter(i -> (i/2)%2==0)
        .takeWhile(i -> i<22)
        .forEach(i -> System.out.print(i+" * "));
        end();
    }

    // test 3
    public static void t3(){
        List.of("giovanni","giacomo","aldo","giovanni").stream()
        .distinct()
        // .sorted((a,b) -> b.length()-a.length())
        .sorted(Comparator.comparing(String::length,Comparator.reverseOrder())) // method reference
        .forEach(s -> System.out.print(s + " - "));
        end();
    }

    // test 4
    public static void t4(){
        // reduce(identity, Bifunction<T,T>)
        // se lo stream è vuoto ritorna la identity, altrimenti ritorna un optional

        System.out.print(
            Stream.of(2,4,6).reduce(0,(a,b)->a+b)
        ); // identità per la somma è 0
        System.out.print(" ");
        Optional<Integer> opt = Stream.of(2,4,6).reduce((a,b)->a*b); // per il prodotto l'identità dovrebbe essere 1
        opt.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.print(t);
            }
        });
        end();
    }

    // test 5
    public static void t5(){ // data una stringa ottenere uno stream dei caratteri
        String s = "ciao questa è una stringa";
        Scanner scan = new Scanner(s);
        System.out.println(
            scan.useDelimiter("").tokens()
            // .collect(Collectors.toList()); // salvare il risultato in una lista
            .collect(Collectors.toCollection(LinkedList::new)) // a parametro un supplier ()->T 
        );
        scan.close();
    }

    // test 6
    public static void t6(){
        System.out.println(
            IntStream.of(4,7,13,21,37,73,12)
            .boxed() // occhio che i primitivi richiedono boxed prima di collect
            .collect(Collectors.toMap(
                i -> Integer.toBinaryString(i),
                i -> i,
                (e,r) -> e,
                TreeMap::new // overloading che aggiugne un supplier () -> T
            ))
        );
    }


    public static void main(String[] args){
        //t1();
        t2();
        t3();
        t4();
        t5();
        t6();
    }
}
     