/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Ahmed
 */
public class Commande {
    
    int id ;
    String nom,total,modepaiement;
    Date date;

    public Commande(int id, String nom, String total, Date date, String modepaiement) {
        this.id = id;
        this.nom = nom;
        this.total = total;
        this.date = date;
        this.modepaiement = modepaiement;
    }

    public Commande() {
    }

    public Commande(String nom, String total, Date date, String modepaiement) {
        this.nom = nom;
        this.total = total;
        this.date = date;
        this.modepaiement = modepaiement;
    }

    public Commande(int id, String nom, String total, String modepaiement) {
        this.id = id;
        this.nom = nom;
        this.total = total;
        this.modepaiement = modepaiement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getModepaiement() {
        return modepaiement;
    }

    public void setModepaiement(String modepaiement) {
        this.modepaiement = modepaiement;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", nom=" + nom + ", total=" + total + ", date=" + date + ", modepaiement=" + modepaiement + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.nom);
        hash = 71 * hash + Objects.hashCode(this.total);
        hash = 71 * hash + Objects.hashCode(this.date);
        hash = 71 * hash + Objects.hashCode(this.modepaiement);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commande other = (Commande) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.modepaiement, other.modepaiement)) {
            return false;
        }
        return true;
    }
    
}
