/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.entities;

import java.util.Objects;

/**
 *
 * @author Ahmed
 */
public class Livraison {
 
    int id ;
    String addresse , details , email,codepostal,phone ;

    public Livraison(int id, String addresse,String codepostal, String email,String phone, String details) {
        this.id = id;
        this.addresse = addresse;
        this.codepostal = codepostal;
        this.email = email;
        this.phone = phone;
        this.details = details;
    }

    public Livraison(String addresse,String codepostal, String email,String phone, String details) {
        this.addresse = addresse;
        this.codepostal = codepostal;
        this.email = email;
        this.phone = phone;
        this.details = details;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", codepostal=" + codepostal + ", phone=" + phone + ", addresse=" + addresse + ", details=" + details + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.id;
        hash = 31 * hash + Objects.hashCode(this.addresse);
        hash = 31 * hash + Objects.hashCode(this.details);
        hash = 31 * hash + Objects.hashCode(this.email);
        hash = 31 * hash + Objects.hashCode(this.codepostal);
        hash = 31 * hash + Objects.hashCode(this.phone);
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
        final Livraison other = (Livraison) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.addresse, other.addresse)) {
            return false;
        }
        if (!Objects.equals(this.details, other.details)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.codepostal, other.codepostal)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return true;
    }
    
    
}
