package tn.edu.foodine.tests;

import tn.edu.foodine.entities.Evenement;
import tn.edu.foodine.entities.Promotion;
import tn.edu.foodine.entities.User;
import tn.edu.foodine.gui.SMS;
import tn.edu.foodine.services.ServiceEvenement;
import tn.edu.foodine.services.ServicePromotion;
import tn.edu.foodine.services.ServiceUtilisateur;
import tn.edu.foodine.services.ProduitCRUD;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import tn.edu.foodine.gui.Mail;

public class MainClass {

    public static void main(String[] args) throws ParseException {
//        String date_string = "2022-01-01";
        LocalDate d = LocalDate.of(2022, 1, 1);

//        System.out.println(LocalDate.of(2022, 1, 1));
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        //Parsing the given String to Date object
//        Date date = formatter.parse(date_string);

//        java.sql.Date d = new java.sql.Date(date.getTime());
        ServiceEvenement se = new ServiceEvenement();
        ServicePromotion sp = new ServicePromotion();
        ServiceUtilisateur su = new ServiceUtilisateur();

        SMS smsClass = new SMS();
        Mail mailClass = new Mail();

        ObservableList<User> usersList;
        usersList = su.getAll2();

        for (User user : usersList) {
            System.out.println(user.getPhone());
        }

//        Evenement e1 = new Evenement(40, "t", Date.valueOf(d) ,Date.valueOf(d) , "", "");
//        se.modifier(e1);
//        se.supprimer(39);
//        List<Evenement> a = se.getAll();
//        System.out.println(a);
//        List<Promotion> p = sp.getAll();
//        System.out.println(p);
        //          System.out.println(sp.countPromotions(3));
//        try {
//            smsClass.sendSMS("Black Friday", Date.valueOf(d), Date.valueOf(d));
//        } catch (Exception ex) {
////        }
//        smsClass.sendSMS("Black Friday", Date.valueOf(d), Date.valueOf(d));
//        mailClass.sendMail("Black Friday", "", Date.valueOf(d), Date.valueOf(d));

        ProduitCRUD pr = new ProduitCRUD();
        
        System.out.println(pr.getProduit());

    }
}
