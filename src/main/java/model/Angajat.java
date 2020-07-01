package model;

public class Angajat {

    private int idAngajat;
    private int idUser;
    private String numeAngajat;
    private String prenumeAngajat;
    private String telefonAngajat;
    private String emailAngajat;

    public Angajat() {
    }

    public Angajat(int idUser, String numeAngajat, String prenumeAngajat, String telefonAngajat, String emailAngajat) {
        this.idUser = idUser;
        this.numeAngajat = numeAngajat;
        this.prenumeAngajat = prenumeAngajat;
        this.telefonAngajat = telefonAngajat;
        this.emailAngajat = emailAngajat;
    }

    public Angajat(String numeAngajat, String prenumeAngajat, String telefonAngajat, String emailAngajat) {
        this.numeAngajat = numeAngajat;
        this.prenumeAngajat = prenumeAngajat;
        this.telefonAngajat = telefonAngajat;
        this.emailAngajat = emailAngajat;
    }

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNumeAngajat() {
        return numeAngajat;
    }

    public void setNumeAngajat(String numeAngajat) {
        this.numeAngajat = numeAngajat;
    }

    public String getPrenumeAngajat() {
        return prenumeAngajat;
    }

    public void setPrenumeAngajat(String prenumeAngajat) {
        this.prenumeAngajat = prenumeAngajat;
    }

    public String getTelefonAngajat() {
        return telefonAngajat;
    }

    public void setTelefonAngajat(String telefonAngajat) {
        this.telefonAngajat = telefonAngajat;
    }

    public String getEmailAngajat() {
        return emailAngajat;
    }

    public void setEmailAngajat(String emailAngajat) {
        this.emailAngajat = emailAngajat;
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "idAngajat=" + idAngajat +
                ", idUser=" + idUser +
                ", numeAngajat='" + numeAngajat + '\'' +
                ", prenumeAngajat='" + prenumeAngajat + '\'' +
                ", telefonAngajat='" + telefonAngajat + '\'' +
                ", emailAngajat='" + emailAngajat + '\'' +
                '}';
    }
}
