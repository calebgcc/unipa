import java.util.*;

public class Main{

    public static boolean isValidEmail(String email){
        return email.matches("\\w{1,}[@](\\w{1,}[.])+[a-z]{2,3}");
    }

    public static String iniziali(String name, String surname){
        return name.charAt(0)+"&"+surname.charAt(0);
    }

    public static String reverseString(String s){
        String result = "";
        for(int i=s.length()-1;i>=0;--i)
            result += s.charAt(i);
        return result;
    }


    // occorrenze di S in T
    public static int contaOccorrenze(String T, String S){
        int count = 0;
        int l = 0,r;
        for(r=1; r<T.length(); ++r){
            if(r-l == S.length()){
                if(T.substring(l,r).equals(S)) ++count;
                ++l;
            }
        }
        if(T.substring(l,r).equals(S)) ++count;

        return count;
    }

    public static void main(String[] args){
            /*System.out.println(iniziali("Ciao","Miao"));
            System.out.println(reverseString("Una mattina, mi sono alzato, o bella ciao, bella ciao, bella ciao ciao ciao"));
            System.out.println(contaOccorrenze("aa aa aaa aaaa","aa"));*/

            System.out.print(":: ");

            Scanner scan = new Scanner(System.in);
            String email = scan.nextLine();

            System.out.println(isValidEmail(email));

            scan.close();
    }

}