
public class Main{
    public static void main(String[] args){
        Serbatoio s1 = new Serbatoio();
        Serbatoio s2 = new Serbatoio(35);

        s1.consuma(7);
        s2.consuma(50);

        System.out.println(s1.getLivello());
        System.out.println(s2.getLivello());
    }
}