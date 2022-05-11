/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.entities;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Admin extends User{

    public Admin() {
    }

    public Admin(int id, String nom, String prenom, String username, String email, String password, String file, Date created_at, int etat) {
        super(id, nom, prenom, username, email, password, file, created_at, etat);
    }

     public Admin(String nom, String prenom, String username, String email, String password, String file, Date created_at, int etat) {
        super(nom, prenom, username, email, password, file, created_at, etat);
    }
    
     public Admin(String nom, String prenom, String username, String email, String password, String file,int etat) {
        super(nom, prenom, username, email, password, file,etat);
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    
    
}
