/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.tests;

import edu.foodinejava.entities.Commande;
import edu.foodinejava.entities.Livraison;
import edu.foodinejava.services.ServiceCommande;
import edu.foodinejava.services.ServiceLivraison;
import edu.foodinejava.utils.DataSource;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Livraison l1 = new Livraison("3","Abdelaziz", "M","dfgs","dgs");
        
        
        ServiceLivraison sp = new ServiceLivraison();
        ServiceCommande sc = new ServiceCommande();
        
        //sp.ajouter(l1);
        //sp.ajouter(p2);
        //sp.ajouter2(l1);
        //sp.ajouter2(p4);*/
        
        List<Commande> a = sc.getAll();
        System.out.println(a);
        
        //sc.supprimer(47);
        //sc.supprimer(49);
        
    }
    
}
