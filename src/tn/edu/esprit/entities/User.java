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
public abstract class User {
    private int id;
    private String nom;
    private String prenom;
    private String username;
    private String email;
    private String password;
    private String file;
    private Date created_at;
    private String reset_token ;
    private int etat;

    public User() {
    }

    public User(int id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public User(int id, String password) {
        this.id = id;
        this.password = password;
    }
    

    public User(int id, String nom, String prenom, String username, String email, String password, String file, Date created_at, int etat) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.file = file;
        this.created_at = created_at;
        this.etat = etat;
    }

    public User(String nom, String prenom, String username, String email, String password, String file, Date created_at, int etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.file = file;
        this.created_at = created_at;
        this.etat = etat;
    }
    
     public User(String nom, String prenom, String username, String email,String file, int etat,Date created_at) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.file = file;
        this.created_at = created_at;
        this.etat = etat;
    }
    
    
   public User(String nom, String prenom, String username, String email, String password, Date created_at, int etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.file = file;
        this.created_at = created_at;
        this.etat = etat;
    }
    
    public User(String nom, String prenom, String username, String email, String password, String file, int etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.file = file;
        this.etat = etat;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
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

    @Override
    public String toString() {
        return "User{" + "nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", email=" + email + ", Role="  + ", file=" + file + ", created_at=" + created_at + ", etat=" + etat + '}';
    }

    
   
}