package jalgo;

import jalgo.datastructures.Jlist;

public class Main{
    public static void main(String[] args){ 

        Jlist<Integer> list = new Jlist<>();
        
        for(int i=0; i<20; i+=2)
            list.append(i);

        list.set(0);
        while(list.isSet()){
            if(list.get()%2 == 0)
                list.insert(list.get()+1);
            list.next();
        }
        
        list.set(list.size()-1);
        while(list.isSet()){
            System.out.println(list);
            if(list.get()==0) break;
            if(list.get()%2 == 0)
                list.remove();
            list.prev();
        }
        list.remove();
        System.out.println(list);
    }
}