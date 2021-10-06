package geometria.solida;

public class Cubo{
    private double lato;

    public Cubo(double lato){
        this.lato = lato;
    }

    public double getVolume(){
        return this.lato*this.lato*this.lato;
    }

    public void setLato(double lato){
        this.lato = lato;
    }

    public double getLato(){
        return this.lato;
    }
}