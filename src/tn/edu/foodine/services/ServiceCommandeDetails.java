/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.services;


import tn.edu.foodine.entities.CommandeDetails;
import tn.edu.foodine.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Parsath
 */
public class ServiceCommandeDetails {
    
    /**
     * This method is used to add an Blog to database
     * @param cd
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean addCommandeDetails(CommandeDetails cd)throws SQLException, ClassNotFoundException{
    Connection cnx = DataSource.getInstance().getCnx();
        String sql = "INSERT INTO `order_products`(`product_id`, `order_id`, `quantity`) VALUES( ?, ?, ?)";
        PreparedStatement stm = cnx.prepareStatement(sql);
        stm.setObject(1, cd.getProduit());
        stm.setObject(2, cd.getOrder());
        stm.setObject(3, cd.getQuantity());
        
        int res = stm.executeUpdate();
        return res > 0;
    }
    
    /**
     * This method is used to search a Blog
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */

    public CommandeDetails searchCommandeDetails(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `order_products` WHERE `order_products`.`order_id` = '"+ id +"'";
    Connection cnx = DataSource.getInstance().getCnx();
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            CommandeDetails cd = new CommandeDetails(rst.getInt(1), rst.getInt(2), rst.getInt(3), rst.getInt(4));
            return cd;
        } else {
            return null;

        }
    }
    /**
     * This method is used to update an Blog if want
     * @param cd
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean updateCommandeDetails(CommandeDetails cd) throws SQLException, ClassNotFoundException{
        
        String sql = "Update `order_products` set `product_id` = ?, `order_id` = ?, `quantity` = ?  WHERE `order_products`.`id` = '" + cd.getId() +"'";
    Connection cnx = DataSource.getInstance().getCnx();
        PreparedStatement stm = cnx.prepareStatement(sql);
        stm.setObject(1, cd.getProduit());
        stm.setObject(2, cd.getProduit());
        stm.setObject(3, cd.getQuantity());
      
        
        int res = stm.executeUpdate();
        return res>0;
    }
    /**
     * This method is used to delete an CommandeDetails if want
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean deleteCommandeDetails(int id) throws SQLException, ClassNotFoundException{
        String sql = "Delete from `order_products` WHERE `order_products`.`id` = '" + id + "'";
    Connection cnx = DataSource.getInstance().getCnx();
        PreparedStatement stm = cnx.prepareStatement(sql);
        stm.setObject(1, id);
        return stm.executeUpdate() > 0;
    }
    
    /**
     * This method is used to take all the CommandeDetails list.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<CommandeDetails> getAllCommandeDetails() throws SQLException, ClassNotFoundException {

    Connection cnx = DataSource.getInstance().getCnx();
        String sql = "Select * from `order_products`";
        Statement statement = cnx.createStatement();
        ResultSet rst = statement.executeQuery(sql);
        ArrayList<CommandeDetails> CommandeDetailsList = new ArrayList<>();
        while (rst.next()) {
            CommandeDetails cd = new CommandeDetails(rst.getInt(1), rst.getInt(2), rst.getInt(3), rst.getInt(4));
            CommandeDetailsList.add(cd);
        }

        return CommandeDetailsList;
    }    
}
