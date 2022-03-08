package geometria;
import geometria.piana.*;
//import geometria.solida.*;

public class Main{
    public static void main(String[] args){
        Quadrilatero q = new Trapezio(new Punto(1.0,1.0), new Punto(2.0,4.0), new Punto(3.0,2.0), new Punto(3.0,1.0));

        System.out.println(q.getArea());
    }
}