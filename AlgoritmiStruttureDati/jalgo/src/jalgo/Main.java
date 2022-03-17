package jalgo;
import jalgo.datastructures.Jlist;

public class Main{
    public static void main(String[] args){ 

        Jlist<Integer> list = new Jlist<>();

        System.out.println(list);

        list.append(3);
        list.append(2);
        list.append(-1);

        System.out.println(list);




    }
}