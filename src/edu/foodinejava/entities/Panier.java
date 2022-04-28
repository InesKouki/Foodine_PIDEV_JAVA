/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.entities;

/**
 *
 * @author Ahmed
 */
public class Panier {
    private Product product;
    private int quantity;

    public Panier(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    
    
     public void decreaseQuantity(){
         if(this.quantity > 0){
             this.quantity--;
         }
    }

    public void increaseQuantity() {
         this.quantity++;  
    }
}
