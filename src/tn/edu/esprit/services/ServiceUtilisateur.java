/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tn.edu.esprit.entities.Admin;
import tn.edu.esprit.entities.Client;

import tn.edu.esprit.entities.User;
import tn.edu.esprit.utils.DataSource;

/**
 *
 * @author Asus
 */
public class ServiceUtilisateur implements IService<User>{
Connection cnx = DataSource.getInstance().getCnx();
String motdepassecrypte;
int i = 0;
StringBuilder finalresult;
ResultSet rs = null;
    
  
        public void ajouterClient(Client u) {
        String request = "INSERT INTO `user`(`nom`, `prenom`, `username`, `email`, `roles`, `password`, `file`, `created_at`,`activation_token`,`phone`,`etat`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
       long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis); 
        try {
            MessageDigest msg = MessageDigest.getInstance("SHA-256");
            byte[] hash = msg.digest(u.getPassword().getBytes(StandardCharsets.UTF_8));
            finalresult = new StringBuilder();
            for (byte b : hash) {
                i++;
                finalresult.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
           }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getUsername());
            ps.setString(4, u.getEmail());
            ps.setString(5,"[]" );
            ps.setString(6, finalresult.toString());
            ps.setString(7, u.getFile());
            ps.setDate(8, date);
            ps.setInt(11,0);
            ps.setString(9,u.getActivation_token());
            ps.setInt(10,u.getPhone());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
    
        public int authentifier(String username, String pwd) throws SQLException {
           
           try {
            MessageDigest msg = MessageDigest.getInstance("SHA-256");
            byte[] hash = msg.digest(pwd.getBytes(StandardCharsets.UTF_8));
            finalresult = new StringBuilder();
            for (byte b : hash) {
                i++;
                finalresult.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));

            }
            System.out.println(finalresult);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }
           
           
       String request = "SELECT * FROM user WHERE username = ? ";
            PreparedStatement ps;
            ps = cnx.prepareStatement(request);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                motdepassecrypte = rs.getString(7);
                if (finalresult.toString().equals(motdepassecrypte)) {
                    return 1;
                }
            }

        return 0;

        }
        
        public User find(int id) {
       String role ="";
        String req="SELECT * FROM user where id="+id+" Limit 1";
        Statement st= null;
        User u =null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
                
            
           while(rs.next()) {
               role =rs.getString("roles");
               if (role.contains("[\"ROLE_ADMIN\"]"))
                u = new Admin();
               else
                   u = new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("username"),rs.getString("email"),rs.getString("password"),rs.getString("file"),rs.getInt("phone"),rs.getString("address"),rs.getInt("etat"));
             
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return u ;
    }

      public User findEmail(String email,String code) {
       
         String req="SELECT * FROM user where email='"+email+"' Limit 1";
        Statement st= null;
        User u =null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
                
            
           while(rs.next()) {
              u = new Client(rs.getString("email"));
             String reqe = "UPDATE `user` SET `reset_token` = '" + code + "' WHERE `user`.`email` = '" + email+"'";
            
            st.executeUpdate(reqe);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return u ;
    }
      
      public User findCode(String code) {
       
         String req="SELECT * FROM user where reset_token='"+code+"' Limit 1";
        Statement st= null;
        User u =null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
                
            
           while(rs.next()) {
              u = new Client(rs.getString("reset_token"));
            
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return u ;
    }
      
        public User findActivationCode(String code) {
       
         String req="SELECT * FROM user where activation_token='"+code+"' Limit 1";
        Statement st= null;
        User u =null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
                
            
           while(rs.next()) {
              u = new Client(rs.getString("nom"),rs.getString("prenom"),rs.getString("username"),rs.getString("email"),rs.getString("password"),rs.getString("file"), rs.getDate("created_at"),rs.getInt("etat"),rs.getString("activation_token"),rs.getInt("phone"));
            
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return u ;
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

    public void Code(String email,String code){
        
        
    }
    //@Override
    public void modifier(Client u) {
         try {
            String req = "UPDATE `user` SET `nom` = '" + u.getNom() + "', `prenom` = '" + u.getPrenom() + "', `phone` = '" + u.getPhone() + "', `address` = '" + u.getAddress() + "', `file` = '" + u.getFile() + "' WHERE `personne`.`id` = " + u.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    public void modifier_Password(User u){
        
         try {
            String req = "UPDATE `user` SET `password` = '" + u.getPassword() + "' WHERE `user`.`id` = " + u.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Mot de passe modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    public void bloquer(int id){
         try {
            String req = "UPDATE `user` SET `etat` = '"+ 0 + "' WHERE `user`.`id` = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur bloqué !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    public void debloquer(int id){
         try {
            String req = "UPDATE `user` SET `etat` = '"+ 1 + "' WHERE `user`.`id` = " +id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur débloqué !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
  /*  public void attribuer_role(User u){
         try {
            String req = "UPDATE `user` SET `roles` = '"+ u.getRoles() + "' WHERE `personne`.`id` = " + u.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Role attribué !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }*/
    
    @Override
    public ObservableList<User> getAll() {
      ObservableList<User> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from user where roles like '%[]%'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                User u = new Client(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"),rs.getString("file"), rs.getInt("etat"),rs.getDate("created_at"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
        public ObservableList<User> getAllTriNom() {
      ObservableList<User> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from user order by nom";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                User u = new Client(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"),rs.getString("file"), rs.getInt("etat"),rs.getDate("created_at"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
        
           public ObservableList<User> getAllTridate() {
      ObservableList<User> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from user order by created_at ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                User u = new Client(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"),rs.getString("file"), rs.getInt("etat"),rs.getDate("created_at"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    


public void changerPass(Client u) {
 try {
            MessageDigest msg = MessageDigest.getInstance("SHA-256");
            byte[] hash = msg.digest(u.getPassword().getBytes(StandardCharsets.UTF_8));
            finalresult = new StringBuilder();
            for (byte b : hash) {
                i++;
                finalresult.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));

            }
            System.out.println(finalresult);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }
   try {
            String req = "UPDATE `user` SET `password` = '"+ finalresult+ "',reset_token='"+NULL
                    +
                     "' WHERE `user`.`reset_token` = '" + u.getReset_token()+"'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("mot de passe modifier modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    public void modifierInfo(Client u) {

   try {
            String req = "UPDATE `user` SET `phone` = '"+ u.getPhone()+ "',prenom='"+u.getPrenom()+ "',nom='"+u.getNom()
                    + "',address='"+u.getAddress()
                    + "',email='"+u.getEmail()
                   
                    
                    + "' WHERE `user`.`id` = " + u.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Information modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

       
  
    
    

    @Override
    public void modifier(User p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public ObservableList<User> chercherUser(String text) {
        
          ObservableList<User> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from user where nom='"+text+"'or prenom='"+text+"' or username='"+text+"' or email='"+text+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                User u = new Client(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"),rs.getString("file"), rs.getInt("etat"),rs.getDate("created_at"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }

    @Override
    public void ajouter(User p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Activer(Client u) {
        try {
           String req = "UPDATE `user` SET `activation_token` = '"+NULL+ "',etat='"+1
                    +
                     "' WHERE `user`.`activation_token` = '" + u.getActivation_token()+"'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Profile Activé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    }
    

