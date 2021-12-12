package main;

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
        DFA uno = new DFA("q",true,true);
        DFA due = new DFA("t");
        DFA tre = new DFA("r");

        uno.link('b',uno); uno.link('c',uno); uno.link('a',due);
        due.link('b',tre);
        tre.link('b',uno);

        DFA.isAccepted(uno,"bccabb",true);
        
    }
}
