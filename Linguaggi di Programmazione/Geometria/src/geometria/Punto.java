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

    public double getSlope(Punto p){
        return (this.compareX(p)==0) ? Double.NaN:(this.compareX(p)*((p.getY()-this.y)/(p.getX()-this.x)));
    }

    public boolean isIncluded(double m,double x,double q){
        if(m == Double.NaN){
            return this.x == x;
        }
        return this.y == (m*(this.x-x)+q);
    }
}
