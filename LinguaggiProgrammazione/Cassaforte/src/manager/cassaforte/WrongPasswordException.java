package manager.cassaforte;

public class WrongPasswordException extends IllegalArgumentException {
    public WrongPasswordException(){
        super("Password errata");
    }
}
