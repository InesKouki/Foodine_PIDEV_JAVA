/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.services;

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
