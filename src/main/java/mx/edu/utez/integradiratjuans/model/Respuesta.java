package mx.edu.utez.integradiratjuans.model;

public class Respuesta {
    private int idRespuesta;
    private int acierto;
    private int idPregunta;

    public Respuesta(int idRespuesta, int acierto, int idPregunta) {
        this.idRespuesta = idRespuesta;
        this.acierto = acierto;
        this.idPregunta = idPregunta;
    }

    public Respuesta() {
    }

    // Getters and setters
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
