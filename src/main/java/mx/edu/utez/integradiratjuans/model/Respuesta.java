package mx.edu.utez.integradiratjuans.model;

public class Respuesta {
    private int id_respuesta;
    private String respuesta;
    private int id_pregunta;

    public Respuesta(int id_respuesta, String respuesta, int id_pregunta) {
        this.id_respuesta = id_respuesta;
        this.respuesta = respuesta;
        this.id_pregunta = id_pregunta;
    }

    public Respuesta() {

    }

    public int getId_respuesta() {
        return id_respuesta;
    }

    public void setId_respuesta(int id_respuesta) {
        this.id_respuesta = id_respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(int id_pregunta) {
        this.id_pregunta = id_pregunta;
    }
}
