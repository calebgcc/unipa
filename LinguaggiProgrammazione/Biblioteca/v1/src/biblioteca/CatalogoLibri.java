package biblioteca;

import java.util.*;

public class CatalogoLibri {
    private ArrayList<Libro> catalogo;

    public CatalogoLibri(){
        this.catalogo = new ArrayList<Libro>();
    }

    public ArrayList<Libro> getCatalogo(){
        return this.catalogo;
    }

    public void stampaCatalogo(){
        for(Libro l : this.catalogo){
            System.out.println(l.toString());
        }
    }

    public void aggiungiLibro(){
        this.catalogo.add(new Libro());
    }

    public void ricerca(String autore){
        boolean flag = true;
        for(Libro l : catalogo){
            for(Autore a : l.getAutori()){
                if(a.toString().equals(autore)){
                    flag = false;
                    System.out.println(l.toString());
                }   
            }
        }
        if(flag)
            System.out.println("[*] Nessun Libro nel Catalogo corrisponde ai criteri di ricerca\n");
    }
}
