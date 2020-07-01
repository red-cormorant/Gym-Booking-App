package model;

public class DetaliiEveniment {
    private int idDetaliiEveniment;
    private int idEveniment;
    private int idClient;

    public DetaliiEveniment() {
    }

    public DetaliiEveniment(int idEveniment, int idClient) {
        this.idEveniment = idEveniment;
        this.idClient = idClient;
    }

    public int getIdDetaliiEveniment() {
        return idDetaliiEveniment;
    }

    public void setIdDetaliiEveniment(int idDetaliiEveniment) {
        this.idDetaliiEveniment = idDetaliiEveniment;
    }

    public int getIdEveniment() {
        return idEveniment;
    }

    public void setIdEveniment(int idEveniment) {
        this.idEveniment = idEveniment;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "DetaliiEveniment{" +
                "idDetaliiEveniment=" + idDetaliiEveniment +
                ", idEveniment=" + idEveniment +
                ", idClient=" + idClient +
                '}';
    }
}
