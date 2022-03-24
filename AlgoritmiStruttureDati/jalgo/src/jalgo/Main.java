package jalgo;


import java.util.Iterator;
import java.util.List;

import jalgo.datastructures.Jlist;

public class Main{

    public static void main(String[] args){ 

        Jlist<String> l = new Jlist<>(List.of("calbe","caleb","foo","bar","buondì"));
        
        for(String s : l)
            System.out.println(s);
        

    }
}