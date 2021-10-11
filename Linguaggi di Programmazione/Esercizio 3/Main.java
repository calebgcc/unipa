public class Main{

    public static String iniziali(String name, String surname){
        return name.charAt(0)+"&"+surname.charAt(0);
    }

    public static String reverseString(String s){
        String result = "";
        for(int i=s.length()-1;i>=0; --i)
            result += s.charAt(i);
        return result;
    }


    // occorrenze di S in T
    public static int contaOccorrenze(String T, String S){
        int count = 0;

        for(int i=0; i<T.length(); ++i){
            for(int j=i+1; j<=T.length();++j){
                if(T.substring(i,j).equals(S)) ++count;
            }
        }

        return count;
    }

    public static void main(String[] args){
            System.out.println(iniziali("Ciao","Miao"));
            System.out.println(reverseString("Una mattina, mi sono alzato, o bella ciao, bella ciao, bella ciao ciao ciao"));
            System.out.println(contaOccorrenze("Rovere di roveri roventi","ove"));
    }

}