package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Rezervare {

    private int idRezervare;
    private int idClient;
    private LocalTime oraRezervare;
    private LocalDate ziRezervare;

    public Rezervare() {
    }

    public Rezervare( LocalTime oraRezervare, LocalDate ziRezervare) {

        this.oraRezervare = oraRezervare;
        this.ziRezervare = ziRezervare;
    }

    public Rezervare(int idClient, LocalTime oraRezervare, LocalDate ziRezervare) {

        this.idClient = idClient;
        this.oraRezervare = oraRezervare;
        this.ziRezervare = ziRezervare;
    }

    public int getIdRezervare() {
        return idRezervare;
    }

    public void setIdRezervare(int idRezervare) {
        this.idRezervare = idRezervare;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public LocalTime getOraRezervare() {
        return oraRezervare;
    }

    public void setOraRezervare(LocalTime oraRezervare) {
        this.oraRezervare = oraRezervare;
    }

    public LocalDate getZiRezervare() {
        return ziRezervare;
    }

    public void setZiRezervare(LocalDate ziRezervare) {
        this.ziRezervare = ziRezervare;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "idRezervare=" + idRezervare +
                ", idClient=" + idClient +
                ", oraRezervare='" + oraRezervare + '\'' +
                ", ziRezervare='" + ziRezervare + '\'' +
                '}';
    }
}
