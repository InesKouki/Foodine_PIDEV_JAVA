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

public class ServicePromotion implements IService<Promotion> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Promotion p) {
        try {
            String req = "INSERT INTO `promotion` (`evenement_id`, `produit_id`, `pourcentage`) VALUES ('" + p.getEvenement_id() + "', '" + p.getProduit_id() + "', '" + p.getPourcentage() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
            String req = "UPDATE `promotion` SET `pourcentage` = '" + p.getPourcentage() + "', `evenement_id` = '" + p.getEvenement_id() + "', `produit_id` = '" + p.getProduit_id() + "' WHERE `promotion`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Promotion> getAll() {
        List<Promotion> list = new ArrayList<>();
        try {
            String req = "Select * from promotion p, evenement e where e.id = p.evenement_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Evenement e = new Evenement(rs.getString("e.name"));
                Produit p = new Produit();
                
                Promotion pr = new Promotion(rs.getDouble("p.pourcentage"), e, p);
                list.add(pr);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
