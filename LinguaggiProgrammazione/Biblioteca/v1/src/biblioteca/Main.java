package biblioteca;

import java.util.*;

public class Main{
    public static Scanner scan;
    public static void main(String[] args){
        Main.scan = new Scanner(System.in);
        Menu.start();
        Main.scan.close();
    }
}