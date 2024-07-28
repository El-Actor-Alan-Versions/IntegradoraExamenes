package mx.edu.utez.integradiratjuans.model;

import java.util.ArrayList;
import java.util.List;

public class Preguntas {
    private String texto;
    private String tipo;
    private String respuesta;
    private List<Opcion> opciones;

    public Preguntas(String texto, String tipo) {
        this.texto = texto;
        this.tipo = tipo;
        this.opciones = new ArrayList<>();
    }

    public void addOpcion(Opcion opcion) {
        this.opciones.add(opcion);
    }

    // Getters y setters
    public String getTexto() { return texto; }
    public String getTipo() { return tipo; }
    public String getRespuesta() { return respuesta; }
    public List<Opcion> getOpciones() { return opciones; }
    public void setRespuesta(String respuesta) { this.respuesta = respuesta; }
}

