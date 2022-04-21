/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static tn.edu.esprit.utils.DataSource.path;

/**
 *
 * @author abdelazizmezri
 */
public class DataSource {
    
    
    
    
    String filename=null;
    public static String path;
    
    
    private Connection cnx;
    private static DataSource instance;
    
    private final String USER = "root";
    private final String PWD = "";
    private final String URL = "jdbc:mysql://localhost:3306/foodinefinalgit";

    public DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DataSource getInstance() {
        if(instance == null)
            instance = new DataSource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    public void filen()
    {try{
        
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("choisir une image");
        chooser.setApproveButtonText("ajouter une image");
     chooser.showOpenDialog(null);
    File f = chooser.getSelectedFile();
    filename=f.getAbsolutePath();
    this.path=(filename);
    }catch(Exception e){
        
        System.out.println(e);
        JOptionPane.showMessageDialog(null,"veuiller choisir une image");}
    
    }
    public String getp(){
    return path;}
  
    
      
    
}
