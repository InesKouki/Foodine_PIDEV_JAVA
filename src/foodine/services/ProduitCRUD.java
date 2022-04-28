/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodine.services;

import foodine.entities.Categorie;
import foodine.entities.Produit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import foodine.utils.MyConnection;
import java.sql.Connection;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author Samar
 */
public class ProduitCRUD {

    Connection cn = MyConnection.getInstance().getCon();

    public void addProduit(Produit p) throws SQLException {
        try {
            String requete = "INSERT INTO `product`(`name`, `price`, `quantitie`, `category_id`, `image`) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(requete);
            ps.setObject(1, p.getName());
            ps.setObject(2, p.getPrice());
            ps.setObject(3, p.getQuantite());
            ps.setObject(4, p.getCategorie().getId());
            ps.setObject(5, p.getImage());
            ps.executeUpdate();
            System.out.println("Produit ajoutée!");

        } catch (SQLException ex) {
        }
    }

    public void supprimerProduit(int id) {
        try {
            String requete = "DELETE FROM `product` WHERE id=" + id + "";
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Produit supprimé!");
        } catch (SQLException ex) {
        }
    }

    public void modifierProduit(Produit p) {
        try {
            String requete = "UPDATE `product` SET `name` = ?, `quantitie` = ?, `price` = ?, `category_id` = ?, `image` = ?  WHERE `id` = " + p.getId();
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setObject(1, p.getName());
            pst.setObject(2, p.getQuantite());
            pst.setObject(3, p.getPrice());
            pst.setObject(4, p.getCategorie().getId());
            pst.setObject(5, p.getImage());
            pst.executeUpdate();
            System.out.println("Produit Modifié!");
        } catch (SQLException ex) {
        }
    }

    public ObservableList<Produit> getProduit() {

        ObservableList<Produit> ProductList = FXCollections.observableArrayList();
        String requete = "SELECT * FROM product p, category c where p.category_id = c.id";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Categorie c = new Categorie(rs.getInt("c.id"), rs.getString("c.name"));
                Produit p = new Produit(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), c, rs.getString(6));
                ProductList.add(p);
            }
        } catch (SQLException ex) {
        }

        return ProductList;
    }

    public ObservableList<Produit> sortNameAsc() {
        ObservableList<Produit> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM product p, category c where p.category_id = c.id order by p.name asc";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Categorie c = new Categorie(rs.getInt("c.id"), rs.getString("c.name"));
                Produit p = new Produit(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), c, rs.getString(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public ObservableList<Produit> sortNameDesc() {
        ObservableList<Produit> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM product p, category c where p.category_id = c.id order by p.name desc";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Categorie c = new Categorie(rs.getInt("c.id"), rs.getString("c.name"));
                Produit p = new Produit(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), c, rs.getString(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public ObservableList<Produit> sortPriceAsc() {
        ObservableList<Produit> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM product p, category c where p.category_id = c.id order by p.price asc";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Categorie c = new Categorie(rs.getInt("c.id"), rs.getString("c.name"));
                Produit p = new Produit(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), c, rs.getString(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public ObservableList<Produit> sortPriceDesc() {
        ObservableList<Produit> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM product p, category c where p.category_id = c.id order by p.price desc";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Categorie c = new Categorie(rs.getInt("c.id"), rs.getString("c.name"));
                Produit p = new Produit(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), c, rs.getString(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
