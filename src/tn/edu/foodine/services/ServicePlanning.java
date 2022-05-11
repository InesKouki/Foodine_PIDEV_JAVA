/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.services;

import tn.edu.foodine.entities.Planning;
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
public class ServicePlanning implements IService<Planning> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Planning p) {
        try {
            String req = "INSERT INTO `planning` (`nom`, `date`) VALUES ('" + p.getNom() + "', '" + p.getDate() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Planning created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(Planning p) {
        try {
            String req = "INSERT INTO `planning` (`nom`, `date`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(2, (Date) p.getDate());
            ps.setString(1, p.getNom());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `planning` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Planning deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Planning p) {
        try {
            String req = "UPDATE `planning` SET `nom` = '" + p.getNom() + "', `date` = '" + p.getDate()+ "' WHERE `planning`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Planning updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Planning> getAll() {
        ObservableList<Planning> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from planning";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Planning p = new Planning(rs.getInt(1), rs.getDate(2), rs.getString(3));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    public ObservableList<String> getAll1() {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String req = "Select nom from planning";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                list.add(rs.getString("nom"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public Planning getNomPlanning(String nom) {
       // List<Planning> list = new ArrayList();
       Planning p=null;
        try {
            String req = "Select * from planning where nom='"+nom+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                 p = new Planning(rs.getInt(1), rs.getDate(2), rs.getString(3));
                //list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }
}
