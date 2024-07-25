package mx.edu.utez.integradiratjuans.model;

public class Division {
    private int idDivision;
    private String nombreDivision;

    public Division() {
    }

    public Division(int idDivision, String nombreDivision) {
        this.idDivision = idDivision;
        this.nombreDivision = nombreDivision;
    }

    public int getIdDivision() {
        return idDivision;
    }

    public void setIdDivision(int idDivision) {
        this.idDivision = idDivision;
    }

    public String getNombreDivision() {
        return nombreDivision;
    }

    public void setNombreDivision(String nombreDivision) {
        this.nombreDivision = nombreDivision;
    }
}
