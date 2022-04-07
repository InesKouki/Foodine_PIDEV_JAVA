package foodine.entities;

public class Produit {

    private int id;
    private String name;
    private double price;
    private int quantite;
    private int categorie;
    private String image;

    public Produit(int id, String name, int quantite, double price, int categorie, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantite = quantite;
        this.categorie = categorie;
        this.image = image;
    }

    public Produit(String name, double price, int quantite, int categorie, String image) {
        this.name = name;
        this.price = price;
        this.quantite = quantite;
        this.categorie = categorie;
        this.image = image;
    }
    
    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "name=" + name + ", price=" + price + ", quantite=" + quantite + ", categorie=" + categorie + ", image=" + image + '}';
    }

}
