package geometria.piana;

public class Quadrato{
    private double lato;

    public Quadrato(double lato){
        this.lato = lato;
    }

    public double getArea(){
        return this.lato*this.lato;
    }

    public void setLato(double lato){
        this.lato = lato;
    }

    public double getLato(){
        return this.lato;
    }
}