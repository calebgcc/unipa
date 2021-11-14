package manager.cassaforte;

import java.util.*;
import java.io.*;
import java.security.*;
import manager.*;


public class Cassaforte implements Serializable{
    private String secret;
    private boolean blocked;
    private boolean open;
    private int tentativi;
    private byte[] password;



    public Cassaforte(){
        this.blocked = false;
        this.tentativi = 0;

        // creazione della password
        try{
            Console cns = System.console();

            char[] firstPsw = null; 
            while(firstPsw==null || firstPsw.length<6){
                System.out.println("[-] Password di almeno 6 caratteri");
                firstPsw= cns.readPassword("Password:: ");
            }

            char[] secondPsw = cns.readPassword("Ripeti Password:: ");
            if(Arrays.equals(firstPsw,secondPsw)){
                this.password = hash(Arrays.toString(firstPsw));
            }
            else{
                throw new PasswordsAreDifferentException();
            }
            this.open = true; // quando open=true è possibile modificare secretArea
        }
        catch(PasswordsAreDifferentException e){
            System.out.println("[!] Le password non corrispondono");
        }
    }

    private byte[] hash(String s){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(s.getBytes("UTF-8"));
        }
        catch(NoSuchAlgorithmException e){
            System.out.println("[!] Servizio non disponibile\n"+e.getMessage());
        }
        catch(UnsupportedEncodingException e){
            System.out.println("[!] Servizio non disponibile\n"+e.getMessage());
        }
        return null;
    }

    public void login(){
        if(this.isBlocked()){
            throw new AppIsBlockedException();
        }
        if(this.isOpen()){
            System.out.println("[*] La cassaforte è aperta");
            return;
        }

        Console cns = System.console();

        char[] psw = null; 
        psw = cns.readPassword("Password:: ");
        if(Arrays.equals(this.password, this.hash(Arrays.toString(psw)))){
            this.tentativi = 0;
            this.open = true;
        }
        else{
            this.tentativi++;
            if(this.tentativi == 3){
                this.blocked = true;
                throw new AppIsBlockedException();
            }
            else 
                throw new WrongPasswordException();
        }

    }

    public void setSecret(){
        if(this.isBlocked()){
            throw new AppIsBlockedException();
        }
        if(!this.isOpen()){
            System.out.println("[*] La Cassaforte è chiusa");
        }
        else{
            System.out.println("[-] Inserisci la frase segreta::");
            this.secret = Menu.scan.nextLine();
        }
    }

    public String getSecret(){
        if(this.isBlocked()){
            throw new AppIsBlockedException();
        }
        if(!this.isOpen()){
            System.out.println("[*] La Cassaforte è chiusa");
            return null;
        }
        return this.secret;
    }

    public boolean isOpen(){
        return this.open;
    }

    public boolean isBlocked(){
        return this.blocked;
    }
}