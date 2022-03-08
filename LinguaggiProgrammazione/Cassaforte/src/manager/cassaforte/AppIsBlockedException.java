package manager.cassaforte;

public class AppIsBlockedException extends RuntimeException {
    public AppIsBlockedException(){
        super("La cassaforte è bloccata");
    }
}
