package main;

import java.util.*;

public class Album {
    private String autore;
    private int anno;
    private String titolo;
    private ArrayList<String> brani;

    public Album(String autore, int anno, String titolo, String... brani) {
        this.autore = autore;
        this.anno = anno;
        this.titolo = titolo;
        this.brani = new ArrayList<String>(Arrays.asList(brani));
    }

    public String getAutore() {
        return autore;
    }


    public int getAnno() {
        return anno;
    }


    public String getTitolo() {
        return titolo;
    }


    public ArrayList<String> getBrani() {
        return brani;
    }
}