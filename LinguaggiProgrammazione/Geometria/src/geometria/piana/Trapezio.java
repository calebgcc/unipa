package geometria.piana;

import geometria.*;


public class Trapezio extends Quadrilatero {

    public Trapezio(Punto a,Punto b,Punto c,Punto d){
        super(a,b,c,d);
        this.test();
    }


    private void notValid() {
        this.setValid(false);
        throw new RuntimeException();
    }

    private void test(){
        
        Punto[] v = this.getVertici();
        
        if(v[0].getSlope(v[1]) == v[2].getSlope(v[3])){
            return;
        }

        if(v[0].getSlope(v[3]) == v[1].getSlope(v[2])){
            return;
        }

        notValid();
    }
}
