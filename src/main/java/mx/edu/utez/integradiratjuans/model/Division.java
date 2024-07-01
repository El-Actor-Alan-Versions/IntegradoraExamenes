package mx.edu.utez.integradiratjuans.model;


public class Division {
    private int id_division;
    private String nombre_division;

    public Division() {
    }

    public Division(int id_division, String nombre_division) {
        this.id_division = id_division;
        this.nombre_division = nombre_division;
    }

    public int getId_division() {
        return id_division;
    }

    public void setId_division(int id_division) {
        this.id_division = id_division;
    }

    public String getNombre_division() {
        return nombre_division;
    }

    public void setNombre_division(String nombre_division) {
        this.nombre_division = nombre_division;
    }
}
