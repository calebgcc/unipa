package biblioteca;


public class Libro {
    public static int ID = 1000;
    private int id;
    private Autore[] autori;
    private String titolo;
    private double prezzo;
    private String casaEditrice;

    public Libro(){
        this.id = Libro.ID++;

        // AUTORI
        System.out.print("N° Autori:: ");
        int size = Main.scan.nextInt();
        Main.scan.nextLine();

        this.autori = new Autore[size];
        for(int i=0; i<size; ++i){
            this.autori[i] = new Autore();
        }

        System.out.print("Titolo:: ");
        this.titolo = Main.scan.nextLine();

        System.out.print("Prezzo:: ");
        this.prezzo = Main.scan.nextDouble();
        Main.scan.nextLine();

        System.out.print("Casa Editrice:: ");
        this.casaEditrice = Main.scan.nextLine();
    }

    public Autore[] getAutori(){
        return this.autori;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("------ "+this.titolo+" -------\n");
        buf.append(this.casaEditrice+" "+this.prezzo+"£\n");
        for(int i=0; i<this.autori.length; ++i){
            buf.append(this.autori[i].toString()+" ");
        }
        buf.append("\nID:: "+this.id+"\n");
        buf.append("------------------------------\n");
        return buf.toString();
    }
}
