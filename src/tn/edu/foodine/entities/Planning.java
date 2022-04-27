/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.entities;

import java.sql.Date;
import javafx.scene.control.Button;
/**
 *
 * @author PC
 */
public class Planning {
    private int id;
    private Date date;
    private String nom;

    public Planning(String nom) {
        this.nom = nom;
    }

    

    public Planning(int id, Date date, String nom) {
        this.id = id;
        this.date = date;
        this.nom = nom;
    }
    public Planning(String nom,Date date) {
       this.nom = nom;
       this.date = date;
    }

    public Planning(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    public Planning() {
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return String.valueOf(this.nom);
    }

    public int compareToIgnoreCase(Planning o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
}
