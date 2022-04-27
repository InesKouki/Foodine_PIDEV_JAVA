/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import tn.edu.foodine.entities.rating;
import tn.foodine.utils.DataSource;

/**
 *
 * @author PC
 */
public class ServiceRating {
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(rating r) throws SQLException {
            String req = "INSERT INTO `rating` (`rating`,`nbUser`,`recette_id`) VALUES ('" + r.getRating()+ "','" + r.getNbUser()+ "', '" + r.getRecette_id().getId()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rating created !");
    }
    public void update(rating r,int id) throws SQLException {
            String req = "UPDATE `rating` SET `rating` = '" + r.getRating()+ "', `nbUser` = '" + r.getNbUser()+ "', `recette_id` = '" + r.getRecette_id().getId() +  "' WHERE `rating`.`id` = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rating modifier !");
    }
    boolean x=false;
    public boolean verif(int idR) throws SQLException{
            String req = "Select * from rating r where recette_id = " + idR;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            if(rs.next()){
                return x=true;
                
            }
                return x;     
    }
    int a;
public int rech(int idr) throws SQLException{
    String req = "Select id from rating r where r.recette_id= " + idr;
    Statement st = cnx.createStatement();
    ResultSet rs = st.executeQuery(req);
    if (rs.next()){
        a=rs.getInt("id");
    } 
    return a;
     
}
public int rechrat(int idrt) throws SQLException{
    String req = "Select rating from rating r where r.id= " + idrt;
    Statement st = cnx.createStatement();
    ResultSet rs = st.executeQuery(req);
    if (rs.next()){
        a=rs.getInt("rating");
    } 
    return a;
}
}
    

