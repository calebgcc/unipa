
/** Conto Bancario */
public class ContoBancario{
    private String instestatario;
    private double saldo;
    private int id;
    private static int contatore;

    /** ContoBancario inizializza il nome intestatario, il saldo e l'id
    @param intestatario nome intestatario
    @param saldo saldo iniziale */
    public ContoBancario(String instestatario,double saldo){
        this.instestatario = instestatario;
        this.saldo = saldo;
        this.id = ContoBancario.contatore++;
    }

    /** incrementa di un valore pari a 'versamento' il saldo
    @param versamento incremento del saldo */
    public void versa(double versamento){
        this.saldo += versamento;
    }

    /** incrementa di un valore pari a 'prelievo' il saldo
    @param prelievo decremento del saldo */
    public void preleva(double prelievo){
        this.saldo -= prelievo;
    }

    /** 'Trasferisce' il valore di somma da this a other
    @param other riferimento all'oggetto che riceve somma
    @param somma valore da prelevare/versare */
    public void bonifico(ContoBancario other, double somma){
        this.preleva(somma);
        other.versa(somma);
    }

    /** Restituisce il saldo
    @return saldo */
    public double getSaldo(){return this.saldo;}

    /** Restituisce l'id
    @return id */
    public int getId(){return this.id;}

}