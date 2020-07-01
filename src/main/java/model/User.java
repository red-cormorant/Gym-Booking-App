package model;

public class User {
    private int idUser;
    private String username;
    private String parola;
    private boolean esteAngajat;

    public User() {
    }

    public User(String username, String parola, boolean esteAngajat) {
        this.username = username;
        this.parola = parola;
        this.esteAngajat = esteAngajat;
    }

    public String getUsername() {
        return username;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public boolean getEsteAngajat() {
        return esteAngajat;
    }

    public void setEsteAngajat(boolean esteAngajat) {
        this.esteAngajat = esteAngajat;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                ", esteAngajat=" + esteAngajat +
                '}';
    }
}
