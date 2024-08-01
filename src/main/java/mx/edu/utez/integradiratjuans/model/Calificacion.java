package mx.edu.utez.integradiratjuans.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Calificacion {
    private int idCalificacion;
    private String matriculaAlumno;
    private int idExamen;
    private double calificacion;
    private Timestamp fecha;

    public Calificacion(String matriculaAlumno, int idExamen, double calificacion, Timestamp fecha) {
        this.matriculaAlumno = matriculaAlumno;
        this.idExamen = idExamen;
        this.calificacion = calificacion;
        this.fecha = fecha;
    }

    public Calificacion() {

    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }
// Getters y setters

    public String getMatriculaAlumno() {
        return matriculaAlumno;
    }

    public void setMatriculaAlumno(String matriculaAlumno) {
        this.matriculaAlumno = matriculaAlumno;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}