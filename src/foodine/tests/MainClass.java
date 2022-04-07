package foodine.tests;

import foodine.entities.Evenement;
import foodine.entities.Promotion;
import foodine.services.ServiceEvenement;
import foodine.services.ServicePromotion;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

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

//        Evenement e1 = new Evenement(40, "t", Date.valueOf(d) ,Date.valueOf(d) , "", "");

//        se.modifier(e1);

//        se.supprimer(39);

//        List<Evenement> a = se.getAll();
//        System.out.println(a);
        
        List<Promotion> p = sp.getAll();
        System.out.println(p);
    }
}
