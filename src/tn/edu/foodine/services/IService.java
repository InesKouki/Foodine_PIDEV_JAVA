/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< Updated upstream:src/edu/foodinejava/services/IService.java
package edu.foodinejava.services;
=======
package tn.edu.foodine.services;
>>>>>>> Stashed changes:src/tn/edu/foodine/services/IService.java

import java.util.List;

/**
 *
 * @author Ahmed
 */
public interface IService <T>{
    public void ajouter(T l);
    public void supprimer(int id);
    public void modifier(T l);
    public List<T> getAll();
    
    
}
