
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.test;

import tn.edu.foodine.entities.Planning;
import tn.edu.foodine.entities.Recette;
import tn.edu.foodine.services.ServicePlanning;
import tn.edu.foodine.services.ServiceRecette;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public class MainClass {
    public static void main(String[] args) throws ParseException {
        String date_string = "2022-01-01";
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        //Parsing the given String to Date object
        Date date = formatter.parse(date_string);

        java.sql.Date d = new java.sql.Date(date.getTime());
        //Planning p =new Planning(31,"Plaaaan 88",d);
        ServicePlanning s=new ServicePlanning();
        s.getAll();
        System.out.println(s);
      //  Recette r = new Recette("Rec1","Abdelaziz", "M","frfr",p);
     
        
        
        //sp.ajouter2(r);
        //sp.supprimer(88);
        //sp.ajouter(p2);
        //sp.ajouter2(t1);
        //sp.ajouter2(p4);
        
        //sp.supprimer(3);
        /*List<Recette> a =
        sp.getAll();
        System.out.println(a);
            */
       /*String date_string = "2022-01-01";
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        //Parsing the given String to Date object
        Date date = formatter.parse(date_string);

        java.sql.Date d = new java.sql.Date(date.getTime());
        Planning p =new Planning("noef",d);*/
        ServiceRecette sr=new ServiceRecette();
        sr.getAll();
        System.out.println(sr);
    
    
    }}
