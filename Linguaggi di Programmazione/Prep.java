import java.util.*;

// preparation for the exam

public class Prep{
    public static void main(String[] args){
        
        System.out.println(Double.parseDouble("10001010"));

    }
}

// S T R I N G
/*

    > equals(string) | equalsIgnoreCase(string) [boolean]
        |
        |_compareTo(string) | compareToIgnoreCase(string) [int]  // lessicografico
        |
        |_ startsWith(string) | endsWith(string) [boolean]
        |
        |_ matches(regex) [boolean]
            |
            |_ replaceFirst(regex,string) | replaceAll(regex,string) [string]
            |
            |_ split(regex) [String[]]

    
    > length() [int]

    > charAt(int) [char]

    > indexOf(char/string) | lastIndexOf(char/string)  [int]

    > toLowerCase() | toUpperCase() | strip() [string]

    > substring(int,int) [string] // [start,end)
        |
        |_ contains(string) [boolean]

    > String.valueOf(tipo primitivo) [string]




                                                                                                                                           
*/

// S T R I N G  B U I L D E R
/*


    > append(string) | insert(int,string)

    > reverse()
    
    > delete(int,int) // [start,end)
        |
        |_ deleteCharAt(int)

    > replace(int,int,string) // [start,end)

    > toString() [string]

    > length() | charAt(int) | substrin(int,int) // funzionano come per le stringhe

*/

// W R A P P E R
/*

    > Charcter.isDigit(char) | Character.isLetter(char) [boolean]
    
    > Integer.parseInt(string,int) [int] // secondo parametro specifica la base // anche per Byte, Short, Long, Float, Double
        |
        |_ Integer.toBinaryString(int) | Integer.toHexString(int) | Integer.toOctalString(int) 

*/
















