package main;

import java.util.*;
import java.util.function.Predicate;

public class Main{

    // test delle interfacce funzionali
    public static boolean test(Integer num, Predicate<Integer> property){
        return property.test(num);
    }


    public static void main(String[] args){
        
        // è pari?
        System.out.println(
            test(5,i -> i%2==0)
        );

        // confronto usando comparing e thenComparing
        List<Dog> list = new LinkedList<>();
        list.add(new Dog("fido",7)); list.add(new Dog("zatura",3));
        list.add(new Dog("terminator",7)); list.add(new Dog("zatura",1));

        list.sort(
            Comparator.comparing(Dog::getNome)
            .thenComparing(Dog::getEta, Comparator.reverseOrder())
        );

        System.out.println(list);

        // Esempi Stream
        EsempioStream.esForEach();
        EsempioStream.esPrimitiveStream();
        EsempioStream.esRandom();
        EsempioStream.esFilter("Io","sto","facendo","un","esempio");
        EsempioStream.esMap("un","altro","esempio");
        EsempioStream.esFlatMap();
        EsempioStream.esLimitSkip();
        EsempioStream.esDistinct("Ciao","ciao","Ciao","miao","miao","caffè");
        EsempioStream.esWhile();
    }
}

class Dog{
    private String nome;
    private int eta;
    public Dog(String nome,int eta){
        this.nome = nome;
        this.eta = eta*7;
    }
    public String getNome(){ return this.nome;}
    public int getEta(){return this.eta;}
    @Override
    public String toString(){ return this.getNome()+" "+this.getEta();}
}