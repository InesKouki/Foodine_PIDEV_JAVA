package foodine.entities;

public class Categorie {

    private int id;
    private String name;
    private String image;

    public Categorie(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public Categorie(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Categorie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Categorie(int id) {
        this.id = id;
    }

    public Categorie() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
