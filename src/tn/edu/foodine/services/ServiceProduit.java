/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.services;

import edu.foodinejava.entities.Commande;
import edu.foodinejava.utils.DataSource;
import java.sql.Connection;
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
 * @author Ahmed
 */
public class ServiceCommande implements IService<Commande> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Commande c) {
        try {
            String req = "INSERT INTO `commande` (`total`, `date`, `modepaiement`, `nom`) VALUES ('" + c.getTotal() + "', '" + c.getDate() + "', '" + c.getModepaiement() + "', '" + c.getNom() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(Commande c) {
        try {
            String req = "INSERT INTO `commande` (`total`, `date`, `modepaiement`, `nom`) VALUES (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getNom());
            ps.setString(2, c.getTotal());
            ps.setDate(3, c.getDate());
            ps.setString(4, c.getModepaiement());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `commande` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Commande c) {
        try {
            String req = "UPDATE `commande` SET total = '" + c.getTotal() + "', date = '" + c.getDate()+ "', modepaiement = '" + c.getModepaiement() + "', nom = '" + c.getNom() +  "' WHERE `commande`.`id` = " + c.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Commande> getAll() {
        ObservableList<Commande> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from commande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Commande c = new Commande(rs.getInt(1), rs.getString(6), rs.getString(2),rs.getDate(3), rs.getString(4));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}

 