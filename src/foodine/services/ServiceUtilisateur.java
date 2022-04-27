/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodine.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import foodine.entities.User;
import foodine.utils.DataSource;

public class ServiceUtilisateur {

    Connection cnx = DataSource.getInstance().getCnx();
    String motdepassecrypte;
    int i = 0;
    StringBuilder finalresult;

    public ObservableList<User> getAll2() {
        ObservableList<User> list = FXCollections.observableArrayList();
        String role = null;
        int phone = 0;
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                role = rs.getString("roles");
                phone = rs.getInt("phone");
                if (!role.contains("[\"ROLE_ADMIN\"]") && phone != 0) {
                    User u = new User(rs.getString("email"),rs.getInt("phone"));
                    list.add(u);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
