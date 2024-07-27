package mx.edu.utez.integradiratjuans.model;

public class Opciones {
    private int id_opcion;
    private String opcion;
    private int id_pregunta;


    public Opciones(int id_opcion, String opcion, int id_pregunta) {
        this.id_opcion = id_opcion;
        this.opcion = opcion;
        this.id_pregunta = id_pregunta;
    }

    public Opciones() {

    }

    public int getId_opcion() {
        return id_opcion;
    }

    public void setId_opcion(int id_opcion) {
        this.id_opcion = id_opcion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public int getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(int id_pregunta) {
        this.id_pregunta = id_pregunta;
    }
}
