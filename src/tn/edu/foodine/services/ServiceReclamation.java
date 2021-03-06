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
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.edu.foodine.entities.Reclamation;
import tn.edu.foodine.entities.Reponse;
import tn.edu.foodine.entities.User;
import tn.edu.foodine.utils.DataSource;

/**
 *
 * @author Asus
 */
public class ServiceReclamation implements IServiceUser<Reclamation> {
Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Reclamation r) {
     try {
            String req = "INSERT INTO `reclamation` (`user_id`, `created_at`,`etat`,`type`,`description`) VALUES ('" + r.getUser().getId()+ "', '" + r.getCreated_at()+ 
                    "', '" +r.getEtat()+ "', '" +r.getType()+ "', '" +r.getDescription()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
       try {
            String req = "DELETE FROM `reclamation` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    public void traiter(int id) {
         try {
            
            String req = "UPDATE `reclamation` SET `etat` = '"+1 + "' WHERE `reclamation`.`id` = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
          
            System.out.println("Reclamation traitée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //
    }
    
    public void repondre(Reponse r){
        try {
            String req = "INSERT INTO `reponse` (`recla_id`, `message`) VALUES ('" + r.getId_rec()+ "', '" + r.getMessage()+"')";
            //String req1 = "UPDATE `reclamation` SET `etat` = '"+1 + "' WHERE `reclamation`.`id` = " + r.getId_rec();
            Statement st = cnx.createStatement();
            //st.executeUpdate(req1);
            st.executeUpdate(req);
            System.out.println("Reponse ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Reclamation> getAll() {
      ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM `reclamation` r  join `User` u  on r.user_id=u.id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                
                Reclamation r = new Reclamation(rs.getInt("id"),rs.getString("u.nom"),rs.getString("u.prenom"),rs.getDate("created_at"), rs.getInt("etat"), rs.getString("type"), rs.getString("description"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public void modifier(Reclamation p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    public ObservableList<Reclamation> getAllTriType() {
      ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM `reclamation` r order by `type` ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                
                Reclamation r = new Reclamation(rs.getInt("id"),rs.getString("u.nom"),rs.getString("u.prenom"),rs.getDate("created_at"), rs.getInt("etat"), rs.getString("type"), rs.getString("description"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
     public ObservableList<Reclamation> getAllTriDate() {
      ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM `reclamation` r order by `Date` ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                
                Reclamation r = new Reclamation(rs.getInt("id"),rs.getString("u.nom"),rs.getString("u.prenom"),rs.getDate("created_at"), rs.getInt("etat"), rs.getString("type"), rs.getString("description"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
