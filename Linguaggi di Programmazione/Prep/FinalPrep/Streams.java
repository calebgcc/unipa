import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
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
            stream.forEach(System.out::println);
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

        System.out.println(
            Stream.of(2,4,6).reduce(100,(a,b)->a+b)
        );

        Optional<Integer> opt = Stream.of(2,4,6).reduce((a,b)->a*b);
        opt.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println(t);
            }
        });
    }


    public static void main(String[] args){
        //t1();
        t2();
        t3();
        t4();
    }
}
     