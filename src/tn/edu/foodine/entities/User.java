package tn.edu.foodine.entities;

import java.sql.Date;

public class User {

    private int id;
    private String nom;
    private String prenom;
    private String username;
    private String email;
    private String password;
    private String file;
    private Date created_at;
    private String reset_token;
    private int etat;
    private int phone;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(String password, String reset_token) {
        this.password = password;
        this.reset_token = reset_token;
    }

    public User(int id, String nom, String prenom, String email, String file) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.file = file;
    }

    public User(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public User(String email, int phone) {
        this.email = email;
        this.phone = phone;
    }

    public User(int id, String nom, String prenom, String username, String email, String password, String file, Date created_at, int etat) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.file = file;
        this.created_at = created_at;
        this.etat = etat;
    }

    public User(String nom, String prenom, String username, String email, String password, String file, Date created_at, int etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.file = file;
        this.created_at = created_at;
        this.etat = etat;
    }

    public User(int id, String nom, String prenom, String username, String email, String file, int etat, Date created_at) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.file = file;
        this.created_at = created_at;
        this.etat = etat;

    }

    public User(String nom, String prenom, String username, String email, String file, int etat, Date created_at) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.file = file;
        this.created_at = created_at;
        this.etat = etat;
    }

    public User(String nom, String prenom, String username, String email, String password, Date created_at, int etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.file = file;
        this.created_at = created_at;
        this.etat = etat;
    }

    public User(String nom, String prenom, String username, String email, String password, String file, int etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.file = file;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" + "nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", email=" + email + ", password=" + password + ", file=" + file + ", created_at=" + created_at + ", reset_token=" + reset_token + ", etat=" + etat + ", phone=" + phone + '}';
    }

}
