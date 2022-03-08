package biblioteca;

public class Autore {
    private String nome;
    private String cognome;

    public Autore(){

        System.out.print("Nome Autore:: ");
        this.nome = Main.scan.nextLine();

        System.out.print("Cognome Autore:: ");
        this.cognome = Main.scan.nextLine();

    }

    public String toString(){
        return this.cognome+" "+this.nome;
    }
}
