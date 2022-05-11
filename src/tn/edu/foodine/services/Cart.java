/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.services;

import tn.edu.foodine.entities.Produit;
import java.util.ArrayList;

/**
 *
 * @author EXTREME-GAMING
 */
public final class Cart {
    
    
    private static ArrayList<Produit> Carte = new ArrayList<>();

    public static ArrayList<Produit> getPanier() {
        return Carte;
    }

    public static void addPanier(Produit e) {
        Carte.add(e);
    }

    public static void resetPanier() {
        Carte.clear();
    }

}
