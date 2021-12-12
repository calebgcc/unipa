package main;

import java.util.Set;

public class Main {
    public static void main(String[] args){
        
        // DFA che riconosce L ={ {1,0}* : |0| = 2n, n>=0}
        DFA[] q = {
            new DFA("q0",true,true),
            new DFA("q1")
        };

        q[0].link('0', q[1]); q[0].link('1', q[0]);
        q[1].link('0', q[0]); q[1].link('1', q[1]);

        DFA.isAccepted(q[0], "10110010");

        // DFA che riconosce L = { {a,b,c}* : dopo ogni 'a' vi sia 'bb' }

        DFA[] g = {
            new DFA("q",true,true),
            new DFA("t"),
            new DFA("r")
        };

        g[0].link('b',g[0]); g[0].link('c',g[0]); g[0].link('a',g[1]);
        g[1].link('b',g[2]);
        g[2].link('b',g[0]);

        DFA.isAccepted(g[0],"abccabb",true);
        

        // NFA che riconosce L = { {a,b,c}*abc } // le stringhe formate da (a,b,c) che terminano con "abc"

        NFA[] grafo = {
            new NFA("i",true,false),
            new NFA("a"),
            new NFA("b"),
            new NFA("c",false,true)
        };

        grafo[0].link('a',grafo[0],grafo[1]); grafo[0].link('b',grafo[0]); grafo[0].link('c',grafo[0]);
        grafo[1].link('b',grafo[2]);
        grafo[2].link('c',grafo[3]);

        System.out.println(
            NFA.isAccepted(Set.of(grafo[0]), "abcc")
        );


    }
}
