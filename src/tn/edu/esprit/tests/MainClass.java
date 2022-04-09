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
import java.sql.Date;
import tn.edu.esprit.entities.Admin;
import tn.edu.esprit.entities.Client;
import tn.edu.esprit.entities.Review;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.services.ServiceAvis;
import tn.edu.esprit.services.ServiceReclamation;
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
    
     //DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
     
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
        System.out.println(date);  
      
        
       Review r1 = new Review(date,2,"testjava","testjava");
       ServiceAvis sa = new ServiceAvis();
       //sa.ajouter(r1);
       System.out.println(sa.getAll());
       ServiceUtilisateur su = new ServiceUtilisateur();
       ServiceReclamation sr= new ServiceReclamation();
       System.out.println(sr.getAll());
       System.out.println(su.getAll());
       User u = new Admin("zz","zz","zz","zz","zz","zz", date,1);
       su.ajouter(u);
      
       
        //sa.supprimer(8);
       //System.out.println(su.getAll()) ;
    }
    
}
