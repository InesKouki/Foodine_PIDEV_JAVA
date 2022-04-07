package foodine.entities;

public class Promotion {

    private int id;
    private double pourcentage;
    private Evenement evenement_id;
    private Produit produit_id;

    public Promotion() {
    }

    public Promotion(double pourcentage, Evenement evenement_id, Produit produit_id) {
        this.evenement_id = evenement_id;
        this.pourcentage = pourcentage;
        this.produit_id = produit_id;
    }

    public Promotion(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Promotion(int id, double pourcentage, Evenement evenement_id, Produit produit_id) {
        this.id = id;
        this.evenement_id = evenement_id;
        this.pourcentage = pourcentage;
        this.produit_id = produit_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evenement getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(Evenement evenement_id) {
        this.evenement_id = evenement_id;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Produit getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(Produit produit_id) {
        this.produit_id = produit_id;
    }

    @Override
    public String toString() {
        return "Promotion{" + "evenement_id=" + evenement_id + ", pourcentage=" + pourcentage + ", produit_id=" + produit_id + '}';
    }

}
