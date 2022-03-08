package biblioteca;

public class Menu {
    public static void start(){
        CatalogoLibri catalogo = new CatalogoLibri();
        int menu = 1;
        while(menu != 4){
            System.out.println("1. Stampa Catalogo\n2. Inserisci un libro\n3. Ricerca un Libro\n4. Esci");
            System.out.print(":: ");
            menu = Main.scan.nextInt();
            Main.scan.nextLine();

            System.out.print("\n");

            switch(menu){
                case 1:
                    catalogo.stampaCatalogo();
                break;

                case 2:
                    catalogo.aggiungiLibro();
                break;

                case 3:
                    System.out.print("Nome complete Autore:: ");
                    catalogo.ricerca(Main.scan.nextLine());
                break;
                default:
                break;
            }
        }
    }
}
