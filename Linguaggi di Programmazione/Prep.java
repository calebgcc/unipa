import java.util.*;

// preparation for the exam

public class Prep{

    public static void main(String[] args){
            Integer[] a = new Integer[0];
            ArrayList<Integer> l = new ArrayList<>();

            l.add(1); l.add(2); l.add(3); l.add(0,4);

            l.toArray(a);

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
    
    > Integer.parseInt(string) [int] // anche per Byte, Short, Long, Float, Double
        |
        |_ Integer.toBinaryString(int) | Integer.toHexString(int) | Integer.toOctalString(int) 

*/

// A R R A Y
/*

    ------------------
    int[] n = new int[3]; 
    int[] n = new int[]{1,2,3}; 
    int[] n = {1,2,3};
    ------------------

    > n.length (attributo)

    > varargs : metodo(int... n){} // con foreach si leggono gli argomenti // puoi passare anche un vettore a parametro

    > Arrays.toString([array]) [string]

    > Arrays.equals([array],[array]) [boolean]

    > Arrays.fill([array],[obj]) | Arrays.fill([array],int,int,[obj]) // [start,end)

    > Array.sort([array]) // inplace

    > Array.binarySearch([array],[obj]) [int]

*/

// A R R A Y   L I S T
/*

   [] Contiene solo riferimenti a oggetti

   > add([obj]) | add(int,[obj])

   > indexOf([obj]) | lastIndexOf([obj]) [int]
        |
        |_ remove(int/[obj])
        |
        |_ contains([obj]) [boolean]

   > size() [int]

   > get(int) [obj] | set(int,[obj])

   > toArray([array])

   > Collections.sort([alist]) | Collections.reverse([alist]) | Collections.shuffle[alist])
   > Collections.max([alist]) | Collections.min([alist]) [obj]
   > Collections.swap([alist],int,int)
   > Collections.binarySearch([alist],[obj]) [int]

*/












