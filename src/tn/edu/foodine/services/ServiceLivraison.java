/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.services;

import tn.edu.foodine.entities.Livraison;
import tn.edu.foodine.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ahmed
 */
public class ServiceLivraison implements IService<Livraison> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Livraison l) {
        try {
            String req = "INSERT INTO `livraison` (`codepostal`, `phone`, `addresse`, `details`,`email`) VALUES ('" + l.getCodepostal() + "', '" + l.getPhone() + "', '" + l.getAddresse() + "', '" + l.getDetails() + "', '" + l.getEmail() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Livraison created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(Livraison l) {
        try {
            String req = "INSERT INTO `livraison` (`codepostal`, `phone`, `addresse`, `details`,`email`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, l.getCodepostal());
            ps.setString(2, l.getPhone());
            ps.setString(3, l.getAddresse());
            ps.setString(4, l.getDetails());
            ps.setString(5, l.getEmail());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `livraison` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Livraison deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Livraison p) {
        try {
            String req = "UPDATE `livraison` SET `addresse` = '" + p.getAddresse() + "', `codepostal` = '" + p.getCodepostal()+ "', `email` = '" + p.getEmail()+ "', `phone` = '" + p.getPhone()+ "', `details` = '" + p.getDetails()+ "' WHERE id = " + p.getId();            
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Livraison updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Livraison> getAll() {
        ObservableList<Livraison> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from livraison";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Livraison l = new Livraison(rs.getInt("id"), rs.getString("addresse"), rs.getString("codepostal"),rs.getString("email"), rs.getString("phone"),rs.getString("details"));
                list.add(l);
                
            }
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}

