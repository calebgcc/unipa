package geometria;
import geometria.piana.*;
import geometria.solida.*;

public class Main{
    public static void main(String[] args){
        Cerchio c = new Cerchio(2);
        Quadrato q = new Quadrato(4);
        Cubo b = new Cubo(6);

        System.out.println("Cerchio: "+c.getArea());
        System.out.println("Quadrato: "+q.getArea());
        q.setLato(5);
        System.out.println("Quadrato: "+q.getArea());
        System.out.println("Cubo: "+b.getVolume());
    }
}