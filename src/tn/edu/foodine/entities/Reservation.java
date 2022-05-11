/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Reservation {
     int id;
     Table tableid;  //Table tableid;
     String nom;
     Date datereservation;
     int mobile;
     String email;

    public Reservation(int id, Table tableid, String nom, Date datereservation, int mobile, String email) {
        this.id = id;
        this.tableid = tableid;
        this.nom = nom;
        this.datereservation = datereservation;
        this.mobile = mobile;
        this.email = email;
    }

    public Reservation(int id, String nom, Date datereservation, int mobile, String email) {
        this.id = id;
        this.nom = nom;
        this.datereservation = datereservation;
        this.mobile = mobile;
        this.email = email;
    }

    public Reservation(Table tableid) {
        this.tableid = tableid;
    }
    
    

    public Reservation(String nom, int mobile) {
        this.nom = nom;
        this.mobile = mobile;
    }
    

    public Reservation(Table tableid, String nom, Date datereservation, int mobile, String email) {
        this.tableid = tableid;
        this.nom = nom;
        this.datereservation = datereservation;
        this.mobile = mobile;
        this.email = email;
    }

    public Reservation(String nom) {
        this.nom = nom;
    }

    public Reservation(Table tableid, String nom) {
        this.tableid = tableid;
        this.nom = nom;
    }

    public Reservation(Table tableid, String nom, Date datereservation) {
        this.tableid = tableid;
        this.nom = nom;
        this.datereservation = datereservation;
    }

    public Reservation(String nom, Date datereservation, int mobile, String email) {
        this.nom = nom;
        this.datereservation = datereservation;
        this.mobile = mobile;
        this.email = email;
    }

    public Reservation() {
    }

    public Reservation(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Table getTableid() {
        return tableid;
    }

    public void setTableid(Table tableid) {
        this.tableid = tableid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDatereservation() {
        return datereservation;
    }

    public void setDatereservation(Date datereservation) {
        this.datereservation = datereservation;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reservation{" + "tableid=" + tableid + ", nom=" + nom + ", datereservation=" + datereservation + ", mobile=" + mobile + ", email=" + email + '}';
    }

   
    
    
     
     
     
}
