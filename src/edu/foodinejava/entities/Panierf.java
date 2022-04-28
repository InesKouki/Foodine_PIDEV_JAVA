/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ahmed
 */
public class Panierf {
    
    private static Panierf INSTANCE;
    
    public static Panierf getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Panierf();
        }
        return INSTANCE;
    }
    
    private Map<String,Panier> entries;
    
    public Panierf (){
        this.entries = new HashMap<>();
    }
    
    public void addProduct(String productName){
        Panier productEntry = entries.get(productName.toUpperCase());
        if (productEntry != null){
            productEntry.increaseQuantity();
        }else{
            Product product = Product.valueOf(productName);
            Panier entry = new Panier(product,1);
            entries.put(productName.toUpperCase(), entry);
        }
    }
    
    
    public void removeProduct(String productName){
        Panier productEntry = entries.get(productName.toUpperCase());
        if (productEntry != null){
            productEntry.decreaseQuantity();
        }
    }
    
    public int getQuantity(String productName){
        Panier entry = entries.get(productName.toUpperCase());
        if (entry != null) {
            return entry.getQuantity();
        }
        return 0;
    }
    
    
    public float calculateTotal(){ 
        float total = 0;
        for(Panier entry:entries.values()){
            float entryCost = entry.getProduct().getPrice()*entry.getQuantity();
            total = total + entryCost ;
        }
        return total;
    }
    
    public List<Panier> getEntries(){
        return new ArrayList<>(entries.values());
    }
}
