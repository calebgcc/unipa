package jalgo;

import jalgo.datastructures.Jlist;

public class Main{
    public static void main(String[] args){ 

        Jlist<Integer> list = new Jlist<>();
        
        list.append(1);
        System.out.println(list);

        list.insert(0,-1);
        System.out.println(list);
    

        list.insert(1,0);
        System.out.println(list);

        list.append(2);
        System.out.println(list);

        list.insert(3,5);
        System.out.println(list);

        list.insert(2,6);
        System.out.println(list);

        list.append(7);
        System.out.println(list);

        list.insert(6,73);
        list.insert(6,37);
        System.out.println(list);

        list.insert(0,44);
        list.insert(9,42);
        System.out.println(list);


        list.set(0);
        while(list.hasNext())
            list.next();
        System.out.println(list.get());
        list.set(list.size()-1);
        while(list.hasPrev())
            list.prev();
        System.out.println(list.get());
    }
}