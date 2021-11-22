package manager.cassaforte;

public class PasswordsAreDifferentException extends IllegalArgumentException {
    public PasswordsAreDifferentException(){
        super("[x] Le password non coincidono");
    }
}
