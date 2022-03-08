
import java.io.*;
import java.util.*;

class Persona implements Serializable{
    private String name;
    private String surname;
    private String id;

    public Persona(String name, String surname, String id){
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public String getName(){return this.name;}
    public String getSurname(){return this.surname;}
    public String getID(){return this.id;}

    @Override
    public String toString(){
        return this.name+" "+this.surname+" "+this.id;
    }
}

class Coda implements Serializable{
    private ArrayList<Persona> coda;

    public Coda(){
        coda = new ArrayList<>();
    }

    public void add(Persona p){
        coda.add(p);
    }

    public void sort(){
        Collections.sort(coda, new Comparator<Persona>(){
                @Override
                public int compare(Persona p, Persona q){
                    if(p.getID() == q.getID()) return 0;
                    return p.getID().compareTo(q.getID());
                }
        });
    }

    @Override
    public String toString(){
        String out = "";
        for(Persona p : coda){
            out += p.toString()+"\n";
        }
        return out;
    }

    public ArrayList<Persona> getCoda(){return this.coda;}
}


public class SecondaParte{
    public static void main(String[] args) throws Exception{
        
    // Java aiuta a gesire  FLUSSI DI ENTITÀ di input/output
    // due principali flussi:
    // > Caratteri (formato testo)
    // > Byte (formato binario)

    // File f = new File("workspace/Linguaggi di Programmazione/Prep/PrimaParte.java");
    
    //  Metodi utili per File
    /*
        f.getAbsolutePath()
        f.getName()
        f.getPath()

        f.exists()
        f.isFile()

        f.length() dimensione in byte

        f.renameTo(File t)
        f.delete()

        f.isDirectory()
        f.mkdir() crea una directory
        f.list() restituisce un array di stringhe (ls)

    */

    // Lettura di un file con SCANNER
    /*
        if(f.exists()){
            Scanner scan = new Scanner(new File("input.txt"));
            while(scan.hasNextLine()){ // scan.hasNext() lettura parola per parola
                System.out.println(scan.nextLine()); // scan.next()
            }
            scan.close();
        }
    */


    // la classe Scanner non è la migliore per gestire Input/Output per i file
    // In java possiamo avvalerci di Reader/Wirter (caratteri) e InputStream/OutputStream (byte)
    //  > FileReader e FileWriter sono consigliati per file di testo (con encoding standard)
    //  > FileInputStream e FileOutputStream sono consigliati per file con encoding particolari (file bin)

    // FileInputStream e FileOutputStream (un byte alla volta)
    /*
        FileOutputStream out = new FileOutputStream("myfile");
        for(int b='A'; b<='Z'; ++b){
            out.write(b);
        }
        out.close();

        FileInputStream in = new FileInputStream("myfile");
        int c;
        while((c = in.read()) != -1){// -1 fine file
            System.out.print((char)c + " ");
        }
        in.close();
    */

    // FileReader e FileWriter (1 carattere alla volta)
    /*
        FileWriter out = new FileWriter("myfile");
        for(int b='A'; b<='Z'; ++b){
            out.write(b);
            out.write(10);
        }
        out.close();

        FileReader in = new FileReader("myfile");
        int c;
        while((c = in.read()) != -1){// -1 fine file
            System.out.print((char)c + " ");
        }
        in.close();
    */

    // È spesso utile leggere/scrivere i file di testo riga per riga
    /*
        PrintWriter out = new PrintWriter(new FileWriter("myfile")); // FileWriter("myfile",true) per l'append
        // abbiamo a disposizione i metodi print println e printf
        out.print("ciao ");
        out.println("chiudiamo questa frase...");
        out.printf("Ciao \n uso il printf \n");
        out.close();

        BufferedReader in = new BufferedReader(new FileReader("myfile"));
        String l;
        while((l = in.readLine()) != null){
            System.out.println(l);
        }
        in.close();
    */

    // Gestione i/o oggetti

        Coda q = new Coda();
        q.add(new Persona("John","Wich","abc123"));
        q.add(new Persona("Habram","Kaggle","ntv687"));
        q.add(new Persona("Jonathan","Wich Below","abc123"));
        q.add(new Persona("Natan","Kore","krn002"));

        q.sort();

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("myfile"));
        out.writeObject(q);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("myfile"));
        Coda rif = (Coda) in.readObject();

        System.out.println(rif);
        in.close();


        

    }
}