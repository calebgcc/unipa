package manager;

import java.io.*;
import java.util.*;

import javax.management.RuntimeErrorException;

import manager.cassaforte.*;


public interface Menu{
    public static Scanner scan = new Scanner(System.in);
    public static void start(){
        File backup = new File("backup.encrypt.data");
        Cassaforte cf = null;
        if(backup.exists()){
            try{
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("backup.encrypt.data"));
                cf =(Cassaforte) in.readObject();
                in.close();
            }
            catch(IOException e){
                System.out.println("[!] Problema con il backup della Cassaforte");
            }
            catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
            if(cf == null){
                throw new RuntimeException();
            }
        }
        else{
            cf = new Cassaforte();
        }

        int scelta = 0;
        do{
            System.out.println("[-] Programma cassaforte");
            System.out.println("[1] Login");
            System.out.println("[2] Stampa secret");
            System.out.println("[3] Modifica secret");
            System.out.println("[4] Modifica Password");
            System.out.println("[5] Esci");
            System.out.print("::");

            scelta = scan.nextInt();
            scan.nextLine();

            switch(scelta){
                case 1:
                    try{
                        cf.login();
                        System.out.println("[*] La cassaforte è aperta");
                    }
                    catch(WrongPasswordException e){
                        System.out.println("[!] Password errata");
                    }
                break;
                case 2:
                    String secret = cf.getSecret();
                    if(secret != null){
                        System.out.println(secret);
                    }
                break;
                case 3:
                    cf.setSecret();
                break;
                case 4:
                    // da fare
                break;
                case 5:
                    System.out.println("[-] Ciao ciao :3");
                break;
                default:
                    System.out.println("[!] Comando non riconosciuto");
                break;
            }

        }while(scelta != 5);

        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("backup.encrypt.data"));
            out.writeObject(cf);
            out.close();
        }
        catch(IOException e){
            System.out.println("[!] Problema con la scrittura del backup della Cassaforte");
        }
    
        scan.close();
    }
}
