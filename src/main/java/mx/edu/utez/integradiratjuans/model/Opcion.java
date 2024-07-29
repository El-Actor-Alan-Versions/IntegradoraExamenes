package mx.edu.utez.integradiratjuans.model;

public class Opcion {
    private int idOpcion;
    private String opcion;
    private int idPregunta;
    private boolean correcta;

    public Opcion(int idOpcion, String opcion, int idPregunta, boolean correcta) {
        this.idOpcion = idOpcion;
        this.opcion = opcion;
        this.idPregunta = idPregunta;
        this.correcta = correcta;
    }

    public Opcion(String opcion, boolean correcta) {
        this.opcion = opcion;
        this.correcta = correcta;
    }

    public Opcion() {}

    // Getters and setters
    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }
}
