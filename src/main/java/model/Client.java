package model;

import java.util.Optional;

public class Client {
    private int idClient;
    private int idUser;
    private String numeClient;
    private String prenumeClient;
    private String telefon;
    private String email;

    public Client() {
    }

    public Client(int idUser, String numeClient, String prenumeClient, String telefon, String email) {
        this.idUser = idUser;
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.telefon = telefon;
        this.email = email;
    }

    public Client(String numeClient, String prenumeClient, String telefon, String email) {
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.telefon = telefon;
        this.email = email;
    }

    public Client(int idClient, String numeClient, String prenumeClient, String telefon) {
        this.idClient = idClient;
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.telefon = telefon;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdUsername() {
        return idUser;
    }

    public void setIdUsername(int idUsername) {
        this.idUser = idUsername;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenumeClient() {
        return prenumeClient;
    }

    public void setPrenumeClient(String prenumeClient) {
        this.prenumeClient = prenumeClient;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", idUser=" + idUser +
                ", numeClient='" + numeClient + '\'' +
                ", prenumeClient='" + prenumeClient + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

