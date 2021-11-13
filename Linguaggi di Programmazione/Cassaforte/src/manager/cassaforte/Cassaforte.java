package manager.cassaforte;

import java.util.*;

import javax.management.RuntimeErrorException;

import java.io.*;
import java.security.*;


class PasswordsAreDifferentException extends IllegalArgumentException{
}

public class Cassaforte{
    private String secret;
    private boolean blocked;
    private boolean open;
    private byte[] password;



    public Cassaforte(){
        this.blocked = false;

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

    public boolean isOpen(){
        return this.open;
    }

    public boolean isBlocked(){
        return this.blocked;
    }
}