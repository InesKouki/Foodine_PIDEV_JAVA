/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.entities.Table;
import tn.edu.esprit.utils.DataSource;


/**
 *
 * @author abdelazizmezri
 */
public class servicetable implements Iservice<Table> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Table t) {
        try {
            String req = "INSERT INTO `table` (`nbplacetable`, `numerotable`,`etat`,`imagetable`) VALUES ('" + t.getNbplacetable() + "','" + t.getNumerotable()+ "','" + t.getEtat() + "','" + t.getImagetable() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Table created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(Table t) {
        try {
            String req = "INSERT INTO `table` (`nbplacetable`, `numerotable`,`etat`,`imagetable`) (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(2, t.getNbplacetable());
            ps.setInt(1, t.getNumerotable());
            ps.setString(3, t.getEtat());
            ps.setString(4, t.getImagetable());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `table` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Table deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Table t) {
        try {
            String req = "UPDATE `table` SET `nb_place_table` = '" + t.getNbplacetable() + "', `numero_table` = '" + t.getNumerotable()  + "', `etat_table` = '" + t.getEtat() + "', `image_table` = '" + t.getImagetable()+   "' WHERE `table`.`id` = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Table updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Table> getAll() {
        List<Table> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `table`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
                Table t = new Table(rs.getInt(1),rs.getInt(2),rs.getString(3), rs.getInt(4),rs.getString(5));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
