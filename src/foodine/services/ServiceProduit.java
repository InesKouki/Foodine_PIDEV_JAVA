/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodine.services;

import foodine.entities.Produit;
import foodine.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceProduit implements IService<Produit> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Produit p) {
        try {
            String req = "INSERT INTO `product` (`name`, `price`, `quantitie`, `category_id`, `image`) VALUES ('" + p.getName()+ "', '" + p.getPrice()+ "', '" + p.getQuantite()+ "', '" + p.getCategorie()+ "', '" + p.getImage()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(Produit p) {
        try {
            String req = "INSERT INTO `product` (`name`, `price`, `quantitie`, `category_id`, `image`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getQuantite());
            ps.setInt(4, p.getCategorie());
            ps.setString(5, p.getImage());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `product` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Produit p) {
        try {
            String req = "UPDATE `product` SET `name` = '" + p.getName()+ "', `price` = '" + p.getPrice()+ "', `quantitie` = '" + p.getQuantite()+ "', `category_id` = '" + p.getCategorie()+ "', `image` = '" + p.getImage()+ "' WHERE `personne`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Produit> getAll() {
        ObservableList<Produit> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from product";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Produit p = new Produit(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDouble(5) , rs.getString(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
