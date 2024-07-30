package mx.edu.utez.integradiratjuans.model;

import java.util.ArrayList;
import java.util.List;

public class Preguntas {

    private int idPregunta;
    private String texto; // Antes llamado 'pregunta'
    private String tipo; // Agregado del modelo 'Preguntas'
    private String respuesta; // Agregado del modelo 'Preguntas'
    private int idExamen;
    private List<Opcion> opciones;

    // Constructor vacío
    public Preguntas() {
        this.opciones = new ArrayList<>();
    }

    // Constructor con dos parámetros
    public Preguntas(String texto, String tipo) {
        this.texto = texto;
        this.tipo = tipo;
        this.opciones = new ArrayList<>();
    }

    // Constructor con cuatro parámetros (si decides usarlo)
    public Preguntas(String texto, String tipo, String respuesta, int idExamen) {
        this.texto = texto;
        this.tipo = tipo;
        this.respuesta = respuesta;
        this.idExamen = idExamen;
        this.opciones = new ArrayList<>();
    }


    // Getters y Setters
    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    public void addOpcion(Opcion opcion) {
        this.opciones.add(opcion);
    }
}
