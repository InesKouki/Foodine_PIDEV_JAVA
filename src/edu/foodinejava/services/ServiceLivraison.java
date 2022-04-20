/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.services;

import edu.foodinejava.entities.Livraison;
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
    public void modifier(Livraison l) {
        try {
            String req = "UPDATE livraison SET addresse = '" + l.getAddresse() + "', codepostal = '" + l.getCodepostal()+ "', email = '" + l.getEmail() + "', phone = '" + l.getPhone()+ "', details = '" + l.getDetails()+ "' WHERE livraison.`id` = " + l.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Livraison updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Livraison> getAll() {
        List<Livraison> list = new ArrayList<>();
        try {
            String req = "Select * from livraison";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Livraison l = new Livraison(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5));
                list.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}

