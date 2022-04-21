/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.tests;

import desktop2.test.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import tn.edu.esprit.entities.Reservation;
import tn.edu.esprit.entities.Table;
import tn.edu.esprit.services.servicereservation;
import tn.edu.esprit.services.servicetable;

/**
 *
 * @author ASUS
 */
public class MainClass {
     public static void main(String[] args) throws ParseException {
        
        Table t1 = new Table(344,2444,"Abdelazizppp", "Mppp");
     
        Table t2=new Table(888,888,"a","a");
        
        //servicetable sp = new servicetable();
        servicereservation sr = new servicereservation();
        //sp.modifier(t2);*/
        
        sr.supprimer(1);
        
        //sp.ajouter(t1);
        //sp.supprimer(88);
        /*sp.ajouter(p2);
        sp.ajouter2(p3);
        sp.ajouter2(p4);
        
        sp.supprimer(3);*/
            /* List<Table> a = sp.getAll();
             
            System.out.println(a);*/
            
            
             /* List<Reservation> b = sr.getAll();
                System.out.println(b);*/
                
               /* String date_string = "2022-01-01";
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        //Parsing the given String to Date object
        Date date = formatter.parse(date_string);

        java.sql.Date d = new java.sql.Date(date.getTime());
                
                
                Reservation r = new Reservation(84, "test",d,1,"test");
                sr.ajouter(r);*/
            
    }
}
