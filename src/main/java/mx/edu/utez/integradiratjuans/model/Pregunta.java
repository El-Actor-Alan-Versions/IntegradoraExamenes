package mx.edu.utez.integradiratjuans.model;



public class Pregunta {

    private int idPregunta;

    private String pregunta;

    private int idExamen;


    public Pregunta() {
    }

    public Pregunta(String pregunta, int idExamen) {
        this.pregunta = pregunta;
        this.idExamen = idExamen;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }
}

