/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.services;


import tn.edu.foodine.entities.Commande;
import tn.edu.foodine.utils.DataSource;
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
import java.sql.Connection;


/**
 *
 * @author Parsath
 */
public class ServiceCommande {
    
    /**
     * This method is used to add an Blog to database
     * @param commande
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean addCommande(Commande commande)throws SQLException, ClassNotFoundException{
    Connection cnx = DataSource.getInstance().getCnx();
        String sql = "INSERT INTO `orders`(`user_id`, `date_created`, `prix`) VALUES( ?, ?, ?)";
        PreparedStatement stm = cnx.prepareStatement(sql);
        stm.setObject(1, commande.getUser());
        stm.setObject(2, commande.getDate());
        stm.setObject(3, commande.getPrix());
        
        int res = stm.executeUpdate();
        return res > 0;
    }
    
    /**
     * This method is used to search a Blog
     * @param c
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */

    public Commande searchCommande(Commande c) throws SQLException, ClassNotFoundException {
        String sql = "Select * from `orders` WHERE (`orders`.`user_id` = '" + c.getUser() + "' and `orders`.`date_created` = '" + c.getDate()+ "' and `orders`.`prix` = '" + c.getPrix()+ "')";
    Connection cnx = DataSource.getInstance().getCnx();
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            Commande cd = new Commande(rst.getInt(1), rst.getInt(2), rst.getDate(3), rst.getFloat(4));
            return cd;
        } else {
            return null;

        }
    }
    /**
     * This method is used to update an Blog if want
     * @param commande
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean updateCommande(Commande commande) throws SQLException, ClassNotFoundException{
        
        String sql = "Update `orders` set (`user`, `date`, `prix`) VALUES( ?, ?, ?)";
    Connection cnx = DataSource.getInstance().getCnx();
        PreparedStatement stm = cnx.prepareStatement(sql);
        stm.setObject(1, commande.getUser());
        stm.setObject(2, commande.getDate());
        stm.setObject(3, commande.getPrix());
      
        
        int res = stm.executeUpdate();
        return res>0;
    }
    /**
     * This method is used to delete an Blog if want
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean deleteCommande(int id) throws SQLException, ClassNotFoundException{
        String sql = "Delete from `orders` where id = ?";
    Connection cnx = DataSource.getInstance().getCnx();
        PreparedStatement stm = cnx.prepareStatement(sql);
        stm.setObject(1, id);
        return stm.executeUpdate() > 0;
    }
    
    /**
     * This method is used to take all the Blog list.
     * @return
     */
    public ObservableList<Commande> getAllCommande() {
        
            ObservableList<Commande> CommandeList = FXCollections.observableArrayList();
    Connection cnx = DataSource.getInstance().getCnx();
        try {
            String sql = "Select * FROM `orders`";
            Statement statement = cnx.createStatement();
            ResultSet rst = statement.executeQuery(sql);
            while (rst.next()) {
                Commande commande = new Commande(rst.getInt(1), rst.getInt(2), rst.getDate(3), rst.getFloat(4));
                CommandeList.add(commande);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
            return CommandeList;
    } 
    
    
}
