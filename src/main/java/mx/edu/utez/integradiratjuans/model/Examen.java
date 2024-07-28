package mx.edu.utez.integradiratjuans.model;

import java.sql.Timestamp;

public class Examen {
    private int id_examen;
    private String nombre;
    private Timestamp fecha_aplicacion;
    private Timestamp fecha_cierre;
    private int id_clase;
    private String descripcion;


    public Examen() {
        // Constructor vacío
    }

    public Examen(int id_examen, String nombre, Timestamp fecha_aplicacion, Timestamp fecha_cierre, int id_clase, String descripcion) {
        this.id_examen = id_examen;
        this.nombre = nombre;
        this.fecha_aplicacion = fecha_aplicacion;
        this.fecha_cierre = fecha_cierre;
        this.id_clase = id_clase;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public void setId_examen(int id_examen) { this.id_examen = id_examen; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setFecha_aplicacion(Timestamp fecha_aplicacion) { this.fecha_aplicacion = fecha_aplicacion; }
    public void setFecha_cierre(Timestamp fecha_cierre) { this.fecha_cierre = fecha_cierre; }
    public void setId_clase(int id_clase) { this.id_clase = id_clase; }

    public int getId_examen() { return id_examen; }
    public String getNombre() { return nombre; }
    public Timestamp getFecha_aplicacion() { return fecha_aplicacion; }
    public Timestamp getFecha_cierre() { return fecha_cierre; }
    public int getId_clase() { return id_clase; }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
