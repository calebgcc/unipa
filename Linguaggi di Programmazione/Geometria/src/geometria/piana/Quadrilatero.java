package geometria.piana;

import geometria.*;

public class Quadrilatero {
    private Punto[] vertice = new Punto[4];
    private double area;
    private boolean valid;


    public Quadrilatero(Punto a, Punto b, Punto c, Punto d){
        
        this.vertice[0] = a;
        this.vertice[1] = b;
        this.vertice[2] = c;
        this.vertice[3] = d;
        
        this.setValid(true);

        this.test();
        this.calcArea();
    }

    private double erone(double a, double b, double c){
        double p = (a+b+c)/2; 
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    private void calcArea(){
        this.area = Math.min(
            this.erone(vertice[0].getDistance(vertice[1]),vertice[1].getDistance(vertice[2]),vertice[0].getDistance(vertice[2]))+
            this.erone(vertice[0].getDistance(vertice[3]),vertice[3].getDistance(vertice[2]),vertice[0].getDistance(vertice[2])),
            this.erone(vertice[0].getDistance(vertice[1]),vertice[1].getDistance(vertice[3]),vertice[0].getDistance(vertice[3]))+
            this.erone(vertice[3].getDistance(vertice[1]),vertice[1].getDistance(vertice[2]),vertice[3].getDistance(vertice[2]))

        );
    }

    private void test(){

        if(Punto.areAligned(vertice[0], vertice[1], vertice[2])){
            notValid();
        }
        else if(Punto.areAligned(vertice[0], vertice[1], vertice[3])){
            notValid();
        }
        else if(Punto.areAligned(vertice[0], vertice[2], vertice[3])){
            notValid();
        }
        else if(Punto.areAligned(vertice[1], vertice[2], vertice[3])){
            notValid();
        }

    }

    private void notValid() {
        throw new RuntimeException();
    }

    public double getArea(){
        this.setValid(false);
        return this.area;
    }

    public boolean isValid(){
        return this.valid;
    }

    public Punto[] getVertici(){
        return this.vertice;
    }

    public void setValid(boolean v){
        this.valid = v;
    }

}
