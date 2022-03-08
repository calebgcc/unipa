
import java.util.*;
import java.io.*;

class StringNotValidExcpetion extends IllegalArgumentException{
    public StringNotValidExcpetion(){
        super("String does not match criteria");
    }
}

public class TerzaParte{

    public static String check(String s){
        if(!s.matches("a[(a|b)]*")){
            throw new StringNotValidExcpetion();
        }
        return s;
    }

    public static void main(String[] args){
        try{
            System.out.println(check("aababba"));
            System.out.println(check("babba"));
        }
        catch(StringNotValidException e){
            System.out.println(e);
        }
        
    }
}