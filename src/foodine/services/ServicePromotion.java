package foodine.services;

import foodine.entities.Evenement;
import foodine.entities.Produit;
import foodine.entities.Promotion;
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

public class ServicePromotion implements IService<Promotion> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Promotion p) throws SQLException{
            String req = "INSERT INTO `promotion` (`evenement_id`, `produit_id`, `pourcentage`) VALUES ('" + p.getEvenement_id().getId() + "', '" + p.getProduit_id().getId() + "', '" + p.getPourcentage() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion created !");
    }

    public void ajouter2(Promotion p) {
        try {
            String req = "INSERT INTO `promotion` (`evenement_id`, `produit_id`, `pourcentage`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getEvenement_id().getId());
            ps.setInt(2, p.getProduit_id().getId());
            ps.setDouble(3, p.getPourcentage());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `promotion` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Promotion p) {
        try {
            String req = "UPDATE `promotion` SET `pourcentage` = '" + p.getPourcentage() + "', `evenement_id` = '" + p.getEvenement_id().getId() + "', `produit_id` = '" + p.getProduit_id().getId() + "' WHERE `promotion`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Promotion> getAll() {
        ObservableList<Promotion> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from promotion p, evenement e, product pr where e.id = p.evenement_id and pr.id = p.produit_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Evenement e = new Evenement(rs.getInt("e.id"),rs.getString("e.name"));
                Produit p = new Produit(rs.getInt("pr.id"),rs.getString("pr.name"));
                
                Promotion pr = new Promotion(rs.getInt("p.id"), (int) Math.round(rs.getDouble("p.pourcentage")*100), e, p);
                list.add(pr);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
