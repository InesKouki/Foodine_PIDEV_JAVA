/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Reclamation {
    private int id;
    private User user;
    private Date created_at;
    private int etat;
    private String type;
    private String description;
    private String nomu;
    private String prenomu;

    public Reclamation (){
        
    }

    public Reclamation(User user, Date created_at, int etat, String type, String description) {
        this.user = user;
        this.created_at = created_at;
        this.etat = etat;
        this.type = type;
        this.description = description;
    }
    
    
    public Reclamation(int id,User user, Date created_at, String type, String description) {
        this.id = id;
        this.user = user;
        this.created_at = created_at;
        this.etat = 0;
        this.type = type;
        this.description = description;
    }

     public Reclamation(String nomu,String prenomu,Date created_at,int etat, String type, String description) {
      this.nomu=nomu;
      this.prenomu=prenomu;
        this.created_at = created_at;
        this.etat = 0;
        this.type = type;
        this.description = description;
    }
     public Reclamation(int id,String nomu,String prenomu,Date created_at,int etat, String type, String description) {
         this.id=id;
      this.nomu=nomu;
      this.prenomu=prenomu;
        this.created_at = created_at;
        this.etat = etat;
        this.type = type;
        this.description = description;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "Utilisateur=" + nomu +" "+ prenomu + ", created_at=" + created_at + ", etat=" + etat + ", type=" + type + ", description=" + description + '}';
    }
    
    
}