
public class Serbatoio{
    private int livello;
    private final int CAPACITA = 50;


    /** Costruttore con livello
    @param livello definisce il livello iniziale */
    Serbatoio(int livello){
        this.livello = livello;
    }

    /** Costruttore senza input, inizializza il livello a 10 */
    Serbatoio(){
        this.livello = 10;
    }

    /** Consuma di un certo decremento il livello
    @param decremento decremento del livello */
    public void consuma(int decremento){
        if(decremento > this.livello){
            System.out.println("Impossibile consumare:" + decremento + "\nIl livello è attualmente:"+ livello + "\n");
        }
        else{
            this.livello -= decremento;
        }
    }
    /** @return restituisce il livello corrente */
    public int getLivello(){
        return this.livello;
    }

    /** @return restituisce la capacità corrente */
    public int getCapacita(){
        return this.CAPACITA;
    }

}