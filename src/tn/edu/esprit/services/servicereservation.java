
package tn.edu.esprit.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.entities.Reservation;
import tn.edu.esprit.entities.Table;

import tn.edu.esprit.utils.DataSource;


/**
 *
 * @author ASUS
 */
public class servicereservation  implements Iservice<Reservation> {
    
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reservation r) {
        try {
            String req = "INSERT INTO `reservation` ( `tableid_id`,`nom`,`datereservation`,`mobile`,`email`) VALUES ('" + r.getTableid()+ "','" + r.getNom() + "','" + r.getDatereservation() + "','" + r.getMobile()+ "','" + r.getEmail() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(Reservation r) {
        try {
            String req = "INSERT INTO `reservation` ( `tableid_id`,`nom`,`datereservation`,`mobile`,`email`)(?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(2, r.getTableid().getId()); // r.getTableid().getId()
            ps.setString(1, r.getNom());
            ps.setDate(3, (Date) r.getDatereservation());
            ps.setInt(5, r.getMobile());
            ps.setString(4, r.getEmail());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reservation` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reservation r) {
        try {
            String req = "UPDATE `reservation` SET `tableid_id` = '" + r.getTableid() + "', `nom` = '" + r.getNom()  + "', `datereservation` = '" + r.getDatereservation() + "', `mobile` = '" + r.getMobile() + "', `email` = '" + r.getEmail()+   "' WHERE `reservation`.`id` = " + r.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "Select * from `table` t ,reservation r where t.id=r.tableid_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Table t1 = new Table(rs.getInt("t.numerotable"));
                Reservation t = new Reservation(t1, rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
}