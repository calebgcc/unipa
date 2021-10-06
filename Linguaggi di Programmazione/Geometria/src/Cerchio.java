package geometria.piana;

// fuori dalla cartella src:
// javac -d ./ src/*.java

// per ottenere il jar eseguibile:
// jar cfe geometria.jar geometria.Main geometria

// per eseguire il jar
// java -jar geometria.jar

public class Cerchio{
    private static final double PI = 3.1415;
    private double raggio;

    public Cerchio(double raggio){
        this.raggio = raggio;
    }

    public double getArea(){
        return 2*this.raggio*Cerchio.PI;
    }

    public void setRaggio(double raggio){
        this.raggio = raggio;
    }

    public double getRaggio(){
        return this.raggio;
    }
}