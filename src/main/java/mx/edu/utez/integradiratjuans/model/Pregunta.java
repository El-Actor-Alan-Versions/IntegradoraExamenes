package mx.edu.utez.integradiratjuans.model;

public class Pregunta {
    private int id_pregunta;
    private String pregunta;
    private int id_examen;

    public Pregunta(int id_pregunta, String pregunta, int id_examen) {
        this.id_pregunta = id_pregunta;
        this.pregunta = pregunta;
        this.id_examen = id_examen;
    }

    public Pregunta() {

    }

    public int getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(int id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getId_examen() {
        return id_examen;
    }

    public void setId_examen(int id_examen) {
        this.id_examen = id_examen;
    }
}
