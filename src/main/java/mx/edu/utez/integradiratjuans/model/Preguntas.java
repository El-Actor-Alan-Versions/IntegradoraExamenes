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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    // Getters y setters
    public String getTexto() { return texto; }
    public String getTipo() { return tipo; }
    public String getRespuesta() { return respuesta; }
    public List<Opcion> getOpciones() { return opciones; }
    public void setRespuesta(String respuesta) { this.respuesta = respuesta; }
}

