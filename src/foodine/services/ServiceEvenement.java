package foodine.services;

import foodine.entities.Evenement;
import foodine.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceEvenement implements IService<Evenement> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Evenement p) throws SQLException {
        String req = "INSERT INTO `evenement` (`name`, `date_deb`, `date_fin`, `description`, `image`) VALUES ('" + p.getName() + "', '" + p.getDate_deb() + "', '" + p.getDate_fin() + "', '" + p.getDescription() + "', '" + p.getImage() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Evenement created !");
    }

    public void ajouter2(Evenement p) {
        try {
            String req = "INSERT INTO `evenement` (`name`, `date_deb`, `date_fin`, `description`, `image`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getName());
            ps.setDate(2, (Date) p.getDate_deb());
            ps.setDate(3, (Date) p.getDate_fin());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getImage());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `evenement` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Evenement p) {
        try {
            String req = "UPDATE `evenement` SET `name` = '" + p.getName() + "', `date_deb` = '" + p.getDate_deb() + "', `date_fin` = '" + p.getDate_fin() + "', `description` = '" + p.getDescription() + "', `image` = '" + p.getImage() + "' WHERE `evenement`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Evenement> getAll() {
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getString(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
