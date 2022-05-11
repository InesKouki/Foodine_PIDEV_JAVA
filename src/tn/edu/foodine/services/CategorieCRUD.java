/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.services;

import tn.edu.foodine.entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.edu.foodine.utils.DataSource;

/**
 *
 * @author windows 10
 */
public class CategorieCRUD {

    Connection cn = DataSource.getInstance().getCnx();

    public void addCategorie(Categorie t) throws SQLException {
        String requete = "INSERT INTO `category`(`name`, `image`) VALUES(?, ?)";
        PreparedStatement pst = cn.prepareStatement(requete);
        pst.setString(1, t.getName());
        pst.setString(2, t.getImage());
        pst.executeUpdate();
        System.out.println("Categorie ajoutée!");
    }

    public void modifierCategorie(Categorie t) {
        try {
            String requete = "UPDATE `category` SET `name` = ?, `image` = ? WHERE `category`.`id` = " + t.getId() + "";

            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, t.getName());
            pst.setString(2, t.getImage());

            pst.executeUpdate();
            System.out.println("Categorie modifiée!");
        } catch (SQLException ex) {
            Logger.getLogger(CategorieCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerCategorie(int id) {
        try {
            String requete = "DELETE FROM `category` WHERE id=" + id + "";
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Categorie supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(CategorieCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Categorie> getCategorie() {

        ObservableList<Categorie> CatList = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `category`";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs;
            try {
                rs = pst.executeQuery(requete);
                while (rs.next()) {
                    Categorie cat = new Categorie(rs.getInt(1), rs.getString(3), rs.getString(2));
                    CatList.add(cat);
                }
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } catch (SQLException ex) {
        }
        return CatList;
    }

}
