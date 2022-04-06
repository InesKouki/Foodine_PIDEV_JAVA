/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tn.edu.esprit.entities.User;
import tn.edu.esprit.utils.DataSource;

/**
 *
 * @author Asus
 */
public class ServiceUtilisateur implements IService<User>{
Connection cnx = DataSource.getInstance().getCnx();
    @Override
  
        public void ajouter(User u) {
       try {
            String req = "INSERT INTO `user` (`nom`, `prenom`,`username`,`email`,`roles`,`password`,`file`,`created_at`) VALUES ('" + u.getNom() + "', '" + u.getPrenom()+ 
                    "', '" +u.getUsername()+ "', '" +u.getEmail()+ "', '" +u.getRoles()+ "', '" +u.getPassword()+u.getCreated_at()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public void supprimer(int id) {
         try {
            String req = "DELETE FROM `user` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User u) {
         try {
            String req = "UPDATE `user` SET `nom` = '" + u.getNom() + "', `prenom` = '" + u.getPrenom() + "', `phone` = '" + u.getPhone() + "', `address` = '" + u.getAddress() + "' WHERE `personne`.`id` = " + u.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    public void modifier_Password(User u){
        
         try {
            String req = "UPDATE `user` SET `password` = '" + u.getPassword() + "' WHERE `personne`.`id` = " + u.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Mot de passe modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    public void bloquer(User u){
         try {
            String req = "UPDATE `user` SET `etat` = '"+ 0 + "' WHERE `personne`.`id` = " + u.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur bloqué !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    public void debloquer(User u){
         try {
            String req = "UPDATE `user` SET `etat` = '"+ 1 + "' WHERE `personne`.`id` = " + u.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur débloqué !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    public void attribuer_role(User u){
         try {
            String req = "UPDATE `user` SET `roles` = '"+ u.getRoles() + "' WHERE `personne`.`id` = " + u.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Role attribué !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    @Override
    public List getAll() {
        List<User> list = new ArrayList<>();
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                User u = new User(rs.getInt(1), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"),rs.getString("roles"),rs.getString("file"),rs.getString("created_at"), rs.getInt("phone"), rs.getString("address"), rs.getInt("etat"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    }
    

