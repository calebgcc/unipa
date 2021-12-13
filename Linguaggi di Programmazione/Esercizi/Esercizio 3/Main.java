import java.util.*;

public class Main{

    public static boolean isValidEmail(String email){
        return email.matches("\\w{1,}[@](\\w{1,}[.])+[a-z]{2,3}");
    }

    // senza regex
    public static boolean isEmailValid(String email){

        //x@x.xx cioè minimo 6 caratteri
        if(email.length() < 6)
            return false;

        // var
        int i, LEN = email.length();
        char ch=' ';

        // prima di arrivare alla chicciola devono essere tutti alfa numerici
        for(i=0; i<LEN; ++i){
            ch = email.charAt(i);
            if(!Character.isLetterOrDigit(ch))
                break;
        }

        
        if(i==LEN || ch!='@')
            return false;

        // - vogliamo solo lettere minuscole e punti
        // prima e dopo un punto ci deve essere una lettera
        int lastDot = -1;
        for(i=i+1; i<LEN; ++i){
            ch = email.charAt(i);
            if(ch == '.'){
                lastDot = i;
                if(ch == '.' && (!Character.isLetter(email.charAt(i-1)) || ((i==LEN-1)||!Character.isLetter(email.charAt(i+1)))))
                    return false;
            }
            else if(!('a'<=ch && ch<='z'))
                return false;
        }

        // voglio controllare che dopo
        if(LEN-lastDot < 3 || LEN-lastDot > 4)
            return false;

        return true;
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

    public static int sumOfDigits(int n){
        if(n < 10)
            return n;
        return n%10 + sumOfDigits(n/10);
    }

    public static boolean isAnagram(String a, String b){

        if(a.length() != b.length())
            return false;

        char[] vetA = a.toCharArray();
        char[] vetB = b.toCharArray();
        Arrays.sort(vetA);
        Arrays.sort(vetB);
        return Arrays.equals(vetA, vetB);
    }

    public static String eliminaVocali(String s){
        StringBuilder buffer = new StringBuilder();
        char ch;
        for(int i=0; i<s.length(); ++i){
            ch = s.charAt(i);
            if(!(ch=='a' || ch=='A' || ch =='e' || ch=='E' || ch=='i' || ch=='I' || ch =='o' || ch=='O' || ch=='u' || ch=='U'))
                buffer.append(ch);
        }
        return buffer.toString();
    }

    public static String intToUnary(int n){
        StringBuilder buffer = new StringBuilder();
        ++n;
        if(n%2 == 1)
            buffer.append('0');
        n/=2;
        for(int i=0; i<n; ++i)
            buffer.append("00");
        return buffer.toString();
    }

    public static String stringCapitalize(String s){
        StringBuilder buffer = new StringBuilder();

        for(int i=0; i<s.length(); ++i){
            if(i==0 || s.charAt(i-1)==' ')
                buffer.append(Character.toUpperCase(s.charAt(i)));
            else
                buffer.append(s.charAt(i));
        }

        return buffer.toString();
    }

    public static String fib(int n){
        if(n==1)
            return "b";
        if(n==0)
            return "a";
        StringBuilder buff = new StringBuilder();
        buff.append(fib(n-1));
        buff.append(fib(n-2));
        return buff.toString();
    }

    public static String randomString(int a, int n){
        char startAlphabet = '0';
        StringBuilder buff = new StringBuilder();

        for(int i=0; i<n; ++i){
            buff.append((char)(startAlphabet+((int)(Math.random()*(a+1)))));
        }
        return buff.toString();
    }

    public static void main(String[] args){
            /*System.out.println(iniziali("Ciao","Miao"));
            System.out.println(reverseString("Una mattina, mi sono alzato, o bella ciao, bella ciao, bella ciao ciao ciao"));
            System.out.println(contaOccorrenze("aa aa aaa aaaa","aa"));*/

            Scanner scan = new Scanner(System.in);

            System.out.print(":: ");

            //String email = scan.nextLine();
            //System.out.println(isValidEmail(email)+" "+isEmailValid(email));

            //System.out.println(sumOfDigits(scan.nextInt()));

            /*String a = scan.nextLine();
            System.out.print(":: ");
            String b = scan.nextLine();
            System.out.println(isAnagram(a, b));*/

            /*String s = scan.nextLine();
            System.out.println(eliminaVocali(s));*/

            /*int n = scan.nextInt();
            System.out.println(intToUnary(n));*/

            // String s = scan.nextLine();
            // System.out.println(stringCapitalize(s));

            // int n = scan.nextInt();
            // System.out.println(fib(n));

            // int a = scan.nextInt();
            // System.out.print(":: ");
            // int n = scan.nextInt();
            // System.out.println(randomString(a, n));



            scan.close();
    }

}