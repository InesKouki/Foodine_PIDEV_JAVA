/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.entities;

/**
 *
 * @author PC
 */
public class rating {
    int id;
    float rating;
    int nbUser;
    Recette recette_id;

    public Recette getRecette_id() {
        return recette_id;
    }

    public rating(int id, float rating, int nbUser, Recette recette_id) {
        this.id = id;
        this.rating = rating;
        this.nbUser = nbUser;
        this.recette_id = recette_id;
    }

    public rating(float rating, int nbUser, Recette recette_id) {
        this.rating = rating;
        this.nbUser = nbUser;
        this.recette_id = recette_id;
    }

    public rating(float rating, int nbUser) {
        this.rating = rating;
        this.nbUser = nbUser;
    }

    public rating(int id, float rating, int nbUser) {
        this.id = id;
        this.rating = rating;
        this.nbUser = nbUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNbUser() {
        return nbUser;
    }

    public void setNbUser(int nbUser) {
        this.nbUser = nbUser;
    }
    
}
