package foodine.entities;

import java.sql.Date;
import javafx.scene.image.ImageView;

public class Evenement {
    private int id;
    private String name;
    private Date date_deb;
    private Date date_fin;
    private String description;
    private String image;
    private ImageView iv;

    public Evenement() {
    }

    public Evenement(String name, Date date_deb, Date date_fin, String description, String image) {
        this.name = name;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.description = description;
        this.image = image;
    }

    public Evenement(int id, String name, Date date_deb, Date date_fin, String description, String image) {
        this.id = id;
        this.name = name;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.description = description;
        this.image = image;
    }

    public Evenement(String name, Date date_deb, Date date_fin, String description) {
        this.name = name;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.description = description;
    }

    public Evenement(String name) {
        this.name = name;
    }

    public Evenement(String name, Date date_deb, Date date_fin, String description, String image, ImageView iv) {
        this.name = name;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.description = description;
        this.image = image;
        this.iv = iv;
    }

    public Evenement(int id, String name, Date date_deb, Date date_fin, String description, String image, ImageView iv) {
        this.id = id;
        this.name = name;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.description = description;
        this.image = image;
        this.iv = iv;
    }

    public Evenement(int id, String name) {
        this.id = id;
        this.name = name;
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

    public Date getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}
