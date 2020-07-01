package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Eveniment {
    private int idEveniment;
    private int idAngajat;
    private int nrMaximParticipanti;
    private String notes;
    private LocalTime oraEveniment;
    private LocalDate ziEveniment;

    public Eveniment(int idAngajat, int nrMaximParticipanti, String notes, LocalTime oraEveniment, LocalDate ziEveniment) {
        this.idAngajat = idAngajat;
        this.nrMaximParticipanti = nrMaximParticipanti;
        this.notes = notes;
        this.oraEveniment = oraEveniment;
        this.ziEveniment = ziEveniment;
    }


    public Eveniment(int nrMaximParticipanti, String notes, LocalTime oraEveniment, LocalDate ziEveniment) {
        this.nrMaximParticipanti = nrMaximParticipanti;
        this.notes = notes;
        this.oraEveniment = oraEveniment;
        this.ziEveniment = ziEveniment;
    }

    public Eveniment() {
    }

    public int getIdEveniment() {
        return idEveniment;
    }

    public void setIdEveniment(int idEveniment) {
        this.idEveniment = idEveniment;
    }

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }

    public int getNrMaximParticipanti() {
        return nrMaximParticipanti;
    }

    public void setNrMaximParticipanti(int nrMaximParticipanti) {
        this.nrMaximParticipanti = nrMaximParticipanti;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalTime getOraEveniment() {
        return oraEveniment;
    }

    public void setOraEveniment(LocalTime oraEveniment) {
        this.oraEveniment = oraEveniment;
    }

    public LocalDate getZiEveniment() {
        return ziEveniment;
    }

    public void setZiEveniment(LocalDate ziEveniment) {
        this.ziEveniment = ziEveniment;
    }

    @Override
    public String toString() {
        return "Eveniment{" +
                "idEveniment=" + idEveniment +
                ", idAngajat=" + idAngajat +
                ", nrMaximParticipanti=" + nrMaximParticipanti +
                ", notes='" + notes + '\'' +
                ", oraEveniment=" + oraEveniment +
                ", ziEveniment=" + ziEveniment +
                '}';
    }
}
