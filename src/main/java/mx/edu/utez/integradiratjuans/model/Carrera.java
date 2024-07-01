package mx.edu.utez.integradiratjuans.model;

public class Carrera {
    private int id_carrera;
    private String nombre_carrera;
    private int id_division;

    public Carrera() {
    }

    public Carrera(int id_carrera, String nombre_carrera, int id_division) {
        this.id_carrera = id_carrera;
        this.nombre_carrera = nombre_carrera;
        this.id_division = id_division;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    public int getId_division() {
        return id_division;
    }

    public void setId_division(int id_division) {
        this.id_division = id_division;
    }
}
