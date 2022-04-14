/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

/**
 *
 * @author ASUS
 */
public class Table {
    private int id,numerotable,nbplacetable;
    private String imagetable,etat;

    public Table(int id, int numerotable, String imagetable, int nbplacetable, String etat) {
        this.id = id;
        this.numerotable = numerotable;
        this.nbplacetable = nbplacetable;
        this.imagetable = imagetable;
        this.etat = etat;
    }

    public Table(int numerotable, int nbplacetable, String imagetable, String etat) {
        this.numerotable = numerotable;
        this.nbplacetable = nbplacetable;
        this.imagetable = imagetable;
        this.etat = etat;
    }

    public Table(int numerotable) {
        this.numerotable = numerotable;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumerotable() {
        return numerotable;
    }

    public void setNumerotable(int numerotable) {
        this.numerotable = numerotable;
    }

    public int getNbplacetable() {
        return nbplacetable;
    }

    public void setNbplacetable(int nbplacetable) {
        this.nbplacetable = nbplacetable;
    }

    public String getImagetable() {
        return imagetable;
    }

    public void setImagetable(String imagetable) {
        this.imagetable = imagetable;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Table{" + "numerotable=" + numerotable + ", nbplacetable=" + nbplacetable + ", imagetable=" + imagetable + ", etat=" + etat + '}';
    }

    
    
}
