package geometria;
import geometria.piana.*;
//import geometria.solida.*;

public class Main{
    public static void main(String[] args){
        Quadrilatero q = new Quadrilatero(new Punto(1,4), new Punto(3,1), new Punto(1,1), new Punto(1,3));
        System.out.println(q.getArea());
    }
}