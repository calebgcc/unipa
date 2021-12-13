public class Main{
    public static void main(String[] args){
        ContoBancario uno = new ContoBancario("Matteo",12.50);
        ContoBancario due = new ContoBancario("Marco", 1004.75);
        ContoBancario tre = new ContoBancario("Luca", 0);
        ContoBancario quattro = new ContoBancario("Giovanni",12_000.60);

        uno.bonifico(tre,10);
        due.bonifico(tre,500);
        quattro.bonifico(tre,1000);

        uno.preleva(0.50);
        due.versa(200);

        System.out.println("UNO "+uno.getSaldo()+" "+uno.getId());
        System.out.println("DUE "+due.getSaldo()+" "+due.getId());
        System.out.println("TRE "+tre.getSaldo()+" "+tre.getId());
        System.out.println("QUATTRO "+quattro.getSaldo()+" "+quattro.getId());
    }
}