package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main{

    private static List<Album> albums;
    private static List<Persona> persone;


    public static void setup(){
        albums = new ArrayList<>();
        albums.add(
            new Album("lennox",1998,"volare","brano 1","brano 2","brano 3")
        );
        albums.add(
            new Album("queen",1960,"top","a1","a2","a3")
        );
        albums.add(
            new Album("jimmy",1968,"fender","c1","d2","f3")
        );
        albums.add(
            new Album("beatles",1956,"tappi","un","due","tre")
        );
        albums.add(
            new Album("sfera",2020,"gang","g","gg","ggg")
        );
        albums.add(
            new Album("777",2007,"diamond","z","zz","zzz")
        );
        albums.add(
            new Album("hillsong",2002,"heaven","sea","sky","mountain")
        );


        persone = new ArrayList<>();
        persone.add(new Persona('m',12));
        persone.add(new Persona('m',16));
        persone.add(new Persona('m',18));
        persone.add(new Persona('m',32));
        persone.add(new Persona('f',45));
        persone.add(new Persona('f',21));
        persone.add(new Persona('m',10));
        persone.add(new Persona('m',28));
    
    }

    public static void main(String[] args) throws IOException{

        setup();

        /* Scrivere con operazioni di aggregazione un’istruzione per stampare le prime 10 canzoni,
           in ordine alfabetico, scritte prima del 2000 
           inserirle all'interno di una mappa, come chiave lunghezza del titolo*/
        Map<Integer,ArrayList<String>> m = albums.stream()
        .filter(a -> a.getAnno() < 2000)
        .map(a -> a.getBrani())
        .flatMap(a -> a.stream())
        .sorted((a,b)-> a.compareTo(b))
        .limit(10)
        .collect(Collectors.groupingBy(
            w -> w.length(),
            Collectors.toCollection(ArrayList::new)
        ));

        System.out.println(m);

        /*Scrivere con operazioni di aggregazione un’istruzione per ottenere
         la media di età di tutte le persone di genere maschile in una lista di persone*/
        double avg = persone.stream()
        .filter(p -> p.getGenere()=='m')
        .mapToInt(p -> p.getEta())
        .summaryStatistics()
        .getAverage();

        System.out.println(avg);

        /*Scrivere con operazioni di aggregazione un’istruzione per ottenere la chiave con valore massimo */
        Map<Character,Integer> mp = new HashMap<>();
        mp.put('a', 12); mp.put('t', 40); mp.put('s',12); mp.put('n',1); mp.put('l',4);

        mp.entrySet().stream()
        .max(
            Comparator.comparing(p -> p.getValue())
        ).ifPresent(p -> System.out.println(p.getKey()));

        /*Scrivere un metodo che prende a parametro un file di testo e restituisce una mappa le cui chiavi
         sono lettere e il valore associato a una lettera è la lista delle righe del file che cominciano con quella lettera*/
        Stream<String> righeFile = Files.lines(Paths.get("main/testo"));
        Map<Character,ArrayList<String>> mpFile = righeFile.collect(
                                                        Collectors.groupingBy(
                                                            s -> s.toLowerCase().charAt(0),
                                                            Collectors.toCollection(ArrayList::new)
                                                        ) );

        System.out.println(mpFile);


    }
}