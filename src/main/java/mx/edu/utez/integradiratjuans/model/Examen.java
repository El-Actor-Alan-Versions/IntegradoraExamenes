package mx.edu.utez.integradiratjuans.model;

import java.util.Date;

public class Examen {
    private int id_examen;
    private String nombre;
    private Date fecha_aplicacion;
    private Date fecha_cierre;
    private int id_clase;

    public Examen(int id_examen, String nombre, Date fecha_aplicacion, Date fecha_cierre, int id_clase) {
        this.id_examen = id_examen;
        this.nombre = nombre;
        this.fecha_aplicacion = fecha_aplicacion;
        this.fecha_cierre = fecha_cierre;
        this.id_clase = id_clase;
    }

    public Examen() {

    }

    public void setId_examen(int id_examen) {
        this.id_examen = id_examen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha_aplicacion(Date fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public void setFecha_cierre(Date fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    public void setId_clase(int id_clase) {
        this.id_clase = id_clase;
    }

    public int getId_examen() {
        return id_examen;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public Date getFecha_cierre() {
        return fecha_cierre;
    }

    public int getId_clase() {
        return id_clase;
    }
}
