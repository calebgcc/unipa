package geometria;
import geometria.piana.*;
//import geometria.solida.*;

public class Main{
    public static void main(String[] args){
        Quadrilatero q = new Quadrilatero(new Punto(0,0), new Punto(0,2), new Punto(3,2), new Punto(3,0));
        System.out.println(q.getArea());
    }
}