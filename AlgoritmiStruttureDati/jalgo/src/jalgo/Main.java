package jalgo;

import jalgo.datastructures.Jlist;

public class Main{
    public static void main(String[] args){ 

        Jlist<Integer> list = new Jlist<>();
        
        for(int i=0;i<20;++i)
            list.append(i);

        list.set(0);

        for(int i=0; i<5; ++i){
            System.out.print(list.get()+" ");
            list.next();
            System.out.println(list.hasNext());
        }

        list.set(12);

        for(int i=0; i<5; ++i){
            System.out.print(list.get()+" ");
            list.prev();
            System.out.println(list.hasPrev());
        }

        list.set(0);
        while(list.hasNext()){
            System.out.print(list.get()+" ");
            list.next();
        }
        System.out.println(list.get());

        while(list.hasPrev()){
            System.out.print(list.get()+" ");
            list.prev();
        }
        System.out.println(list.get());

        while(list.isSet()){
            System.out.print(list.get()+" ");
            list.next();
        }

    }
}