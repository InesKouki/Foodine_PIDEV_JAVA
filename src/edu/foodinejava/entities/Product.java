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
public enum Product {
    
    pizza("pizza.jpg",15f),burger("burger.jpg",6.5f);
    
    private String imageFile;
    private float price;

Product(String imageFile, float price){
    this.imageFile = imageFile;
    this.price = price;
}

public String getImageFile(){
    return imageFile;
}

public float getPrice(){
    return this.price;
}
}
