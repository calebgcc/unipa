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

        if(this.isValid()){
            this.calcArea();
        }
        else{
            System.out.println("[!] Non è un quadrilatero valido");
        }
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

        if(vertice[1].isIncluded(vertice[0].getSlope(vertice[2]), vertice[0].getX(), vertice[0].getY())){
            this.setValid(false);
            return;
        }

        if(vertice[1].isIncluded(vertice[0].getSlope(vertice[3]), vertice[0].getX(), vertice[0].getY())){
            this.setValid(false);
            return;
        }

        if(vertice[2].isIncluded(vertice[0].getSlope(vertice[3]), vertice[0].getX(), vertice[0].getY())){
            this.setValid(false);
            return;
        }

        if(vertice[2].isIncluded(vertice[1].getSlope(vertice[3]), vertice[1].getX(), vertice[1].getY())){
            this.setValid(false);
            return;
        }

    }

    public double getArea(){
        return this.isValid() ? this.area:Double.NaN;
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
