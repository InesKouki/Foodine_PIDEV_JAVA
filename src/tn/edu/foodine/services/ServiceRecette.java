/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.services;

import tn.edu.foodine.entities.Planning;
import tn.edu.foodine.entities.Recette;
import tn.edu.foodine.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceRecette implements IService<Recette> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Recette r) {
        try {
            String req = "INSERT INTO `recette` (`nom`,`description`,`imagerecette`, `ingredient`,`planningid_id`) VALUES ('" + r.getNom() + "','" + r.getDescription()+ "', '" + r.getImage() + "', '" + r.getIngredient()+ "', '" + r.getPlanning().getId()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Recette created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void ajouter2(Recette r) {
        try {
            String req = "INSERT INTO `recette` (`nom`,`description`,`imagerecette`, `ingredient`,`planningid_id`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, r.getNom());
            ps.setString(2, r.getDescription());
            ps.setString(3, r.getImage());
            ps.setString(4, r.getIngredient());
            ps.setInt(5, r.getPlanning().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `recette` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Recette deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Recette r) {
        try {
            String req = "UPDATE `recette` SET `nom` = '" + r.getNom() + "', `description` = '" + r.getDescription()  + "', `imagerecette` = '" + r.getImage()+ "', `ingredient` = '" + r.getIngredient()+ "', `planningid_id` = '" + r.getPlanning().getId()+ "' WHERE `recette`.`id` = " + r.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("recette updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Recette> getAll() {
        ObservableList<Recette> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from recette r, planning p where p.id = r.planningid_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Planning p = new Planning(rs.getInt("p.id"), rs.getString("p.nom"));
                Recette r = new Recette(rs.getInt("r.id"),rs.getString("r.nom"),rs.getString("r.description"),rs.getString("r.imagerecette"),rs.getString("r.ingredient"), p);
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    
    public ObservableList<Recette> getAll2() {
        ObservableList<Recette> list = FXCollections.observableArrayList();
        try {
            String req = "Select r.*, p.nom,p.id from recette r, planning p where p.id = r.planningid_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Planning p = new Planning(rs.getInt("p.id"), rs.getString("p.nom"));
                Recette r = new Recette(rs.getInt("r.id"),rs.getString("r.nom"),rs.getString("r.description"),rs.getString("r.imagerecette"),rs.getString("r.ingredient"),p);
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    /*public void rating(float rating,Recette r) throws SQLException{
        String req = "UPDATE `recette` SET `nomUser` = '" + (r.getNbUser()+1) + "' `Rating` = '" + ((r.getRating()+rating)/r.getNbUser())+ "' WHERE `recette`.`id` = " + r.getId();
        
    }*/
}
