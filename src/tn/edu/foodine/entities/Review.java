/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.entities;


import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Asus
 */
public class Review {
    private int id;
    private String user_name;
    private Date published_at;
    private int stars;
    private String description;

    public Review() {
    }

    public Review(Date published_at, int stars,String user_name,  String description) {
        this.user_name = user_name;
        this.published_at = published_at;
        this.stars = stars;
        this.description = description;
    }

    public Review(int id,Date published_at, int stars, String user_name, String description) {
        this.id = id;
        this.user_name = user_name;
        this.published_at = published_at;
        this.stars = stars;
        this.description = description;
    }
    public Review(int stars, String user_name, String description) {
        this.user_name = user_name;
        this.stars = stars;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getPublished_at() {
        return published_at;
    }

    public void setPublished_at(Date published_at) {
        this.published_at = published_at;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Review{"  + ", user_name=" + user_name + ", published_at=" + published_at + ", stars=" + stars + ", description=" + description + '}';
    }
    
    
    
    
}
