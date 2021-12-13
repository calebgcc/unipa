package main;

import java.util.Set;

public class Main {
    public static void main(String[] args){
        
        Graph g = new Graph(3);

        g.link(1,'a',2);
        g.link(1,'c',1);
        g.link(1,'b',3);
        g.link(2,'c',Set.of(1,3));
        g.link(2,'a',1);
        g.set(1,State.INITIAL);
        g.set(3,State.FINAL);

        System.out.println(
            g.run("aab",true)
        );
    }
}
