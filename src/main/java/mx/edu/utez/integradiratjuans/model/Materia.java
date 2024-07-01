package mx.edu.utez.integradiratjuans.model;

public class Materia {
    private int id_materia;
    private String nombre_materia;

    public Materia() {
    }

    public Materia(int id_materia, String nombre_materia) {
        this.id_materia = id_materia;
        this.nombre_materia = nombre_materia;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }
}


