package manager.cassaforte;

public class PasswordsAreDifferentException extends IllegalArgumentException {
    public PasswordsAreDifferentException(){
        super("Le password non coincidono");
    }
}
