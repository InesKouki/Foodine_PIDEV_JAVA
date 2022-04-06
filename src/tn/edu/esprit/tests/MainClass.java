/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import tn.edu.esprit.entities.Review;
import tn.edu.esprit.services.ServiceAvis;
import tn.edu.esprit.services.ServiceUtilisateur;
import tn.edu.esprit.utils.DataSource;

/**
 *
 * @author Asus
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
      Date date = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        System.out.println(formatter.format(date));
        
         Review r1 = new Review(formatter.format(date),2,"testjava","testjava");
        
        ServiceAvis sa = new ServiceAvis();
         ServiceUtilisateur su = new ServiceUtilisateur();
        //sa.ajouter(r1);
        //sa.supprimer(8);
       System.out.println(su.getAll()) ;
    }
    
}
