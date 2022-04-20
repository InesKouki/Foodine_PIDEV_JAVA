/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Client extends User{
    private int phone;
    private String address;
    private String activation_token ;

    public Client() {
    }

    public Client(int id, String nom, String prenom, String username, String email, String password, String file, Date created_at, int etat) {
        super(id, nom, prenom, username, email, password, file, created_at, etat);
    }
    
    public Client(int id, String nom, String prenom, String email,int phone,String address) {
        super(id, nom, prenom, email);
        this.phone=phone;
        this.address=address;
    }

    public Client(int id, String password) {
        super(id, password);
    }

    
    public Client(String nom, String prenom, String username, String email, String password, String file, Date created_at, int etat) {
        super(nom, prenom, username, email, password, file, created_at, etat);
    }
    
    public Client(String nom, String prenom, String username, String email, String password, Date created_at, int etat) {
        super(nom, prenom, username, email, password, created_at, etat);
    }
    
    public Client(String nom, String prenom, String username, String email,String file, int etat, Date created_at) {
        super(nom, prenom, username, email,file,etat,created_at);
    }
    
    public Client(int id,String nom, String prenom, String username, String email,String file, int etat, Date created_at) {
        super(id,nom, prenom, username, email,file,etat,created_at);
    }
   
   public Client(String nom, String prenom, String username, String email, String password, String file,int etat) {
        super(nom, prenom, username, email, password, file,etat);
    }
    
   public Client(String nom, String prenom, String username, String email, String password, String file,int phone,String address,int etat) {
        super(nom, prenom, username, email, password, file,etat);
        this.address=address;
        this.phone=phone;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    @Override
    public String toString() {
        return super.toString()+ "phone=" + phone + ", address=" + address + ", activation_token=" + activation_token + '}';
    }
  
   
    
    
    
}
