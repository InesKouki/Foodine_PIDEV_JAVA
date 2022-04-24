/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.tests;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import tn.edu.esprit.entities.Admin;
import tn.edu.esprit.entities.Client;
import tn.edu.esprit.entities.Review;
import tn.edu.esprit.entities.User;
import static tn.edu.esprit.gui.ConnexionController.ACCOUNT_SID;
import static tn.edu.esprit.gui.ConnexionController.AUTH_TOKEN;
import tn.edu.esprit.services.ServiceAvis;
import tn.edu.esprit.services.ServiceReclamation;
import tn.edu.esprit.services.ServiceUtilisateur;
import tn.edu.esprit.utils.DataSource;
import tn.edu.esprit.utils.JavaMail;

/**
 *
 * @author Asus
 */

public class MainClass {
 public static final String ACCOUNT_SID="AC0c6aefab9c7673bcc184a93c7b3faade";
    public static final String AUTH_TOKEN="6f4bd195f6994c9feb1aeb3a90c0e4df";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
    
     //DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
     
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
        System.out.println(date);  
      
        
     
       ServiceUtilisateur su = new ServiceUtilisateur();
      
               
        //sa.supprimer(8);
       //System.out.println(su.getAll()) ;
      // JavaMail.sendMail("ines.kouki@esprit.tn", "test");
    }
    
}
