package mx.edu.utez.integradiratjuans.model;

public class Respuesta {
    private int idRespuesta;
    private int acierto;
    private int idPregunta;
    private String matriculaEstudiante;

    public Respuesta(int idRespuesta, int acierto, int idPregunta,  String matriculaEstudiante) {
        this.idRespuesta = idRespuesta;
        this.acierto = acierto;
        this.idPregunta = idPregunta;
        this.matriculaEstudiante = matriculaEstudiante;
    }


    public Respuesta() {
    }

    // Getters and setters

    public String getMatriculaEstudiante() {
        return matriculaEstudiante;
    }

    public void setMatriculaEstudiante(String matriculaEstudiante) {
        this.matriculaEstudiante = matriculaEstudiante;
    }

    public int getAcierto() {
        return acierto;
    }

    public void setAcierto(int acierto) {
        this.acierto = acierto;
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }
}
