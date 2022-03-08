package geometria;

public class Punto {
    private double x,y;

    public Punto(double x, double y){
        this.x = x;
        this.y= y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getDistance(Punto p){
        return Math.sqrt(Math.pow((this.x-p.getX()),2)+Math.pow((this.y-p.getY()),2));
    }


    private int compareX(Punto p){
        if(p.getX() > this.x)
            return 1;
        else if(p.getX() < this.x)
            return -1;
        return 0;
    }

    public static boolean areAligned(Punto a,Punto b, Punto c){

        Double slope = a.getSlope(c);

        if(slope.isNaN()){
            System.out.println(a.getX() + " - " + b.getX());
            return a.getX() == b.getX();
        }

        // the formula is y = slope(x-ax) + ay
        System.out.println(b.getY() + " " + (slope*(b.getX()-a.getX())+a.getY()));
        return Math.round(b.getY()) == (slope*(b.getX()-a.getX())+a.getY());
    }

    public double getSlope(Punto p){
        return (this.compareX(p)==0) ? Double.NaN:(this.compareX(p)*((p.getY()-this.y)/(p.getX()-this.x)));
    }

}
