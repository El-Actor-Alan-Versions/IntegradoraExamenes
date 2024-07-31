package mx.edu.utez.integradiratjuans.model;

public class Respuesta {
    private int idRespuesta;
    private String respuesta;
    private int acierto;
    private int idPregunta;

    public Respuesta(int idRespuesta, String respuesta, int acierto, int idPregunta) {
        this.idRespuesta = idRespuesta;
        this.respuesta = respuesta;
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

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }
}
