package foodine.tests;

import foodine.entities.Categorie;
import foodine.entities.Produit;
import foodine.services.CategorieCRUD;
import foodine.services.ProduitCRUD;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CategorieCRUD sc = new CategorieCRUD();
        ProduitCRUD sp = new ProduitCRUD();
        
        
        Categorie c = new Categorie("testCat", "");
        Produit p = new Produit("tesProd2", 50, (double) 10, c, "");
        
        
       //sc.ajouter(c);
//         sp.ajouter(p);
//sc.supprimer(1);
    
        
        
        /*
        List<Categorie> a = sc.getCategorie();
        System.out.println(a);
        */
        
    }
}