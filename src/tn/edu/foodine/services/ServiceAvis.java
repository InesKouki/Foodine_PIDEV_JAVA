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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import tn.edu.foodine.entities.Review;
import tn.edu.foodine.utils.DataSource;

/**
 *
 * @author Asus
 */
public class ServiceAvis implements IServiceUser<Review>{
    
 Connection cnx = DataSource.getInstance().getCnx();
 
    @Override
    public void ajouter(Review r) {
       try {
            String req = "INSERT INTO `review` (`published_at`, `stars`,`description`,`user_name`) VALUES ('" + r.getPublished_at() + "', '" + r.getStars()+ "', '" +r.getDescription()+ "', '" +r.getUser_name()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Avis ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
          try {
            String req = "DELETE FROM `review` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Avis supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    @Override
    public List getAll() {
       List<Review> list = new ArrayList<>();
        try {
            String req = "Select * from review";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
            
                Review r = new Review(rs.getDate("published_at"),rs.getInt("stars"),rs.getString("description"),rs.getString("user_name"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    @Override
    public void modifier(Review r) {
        
    }
}
