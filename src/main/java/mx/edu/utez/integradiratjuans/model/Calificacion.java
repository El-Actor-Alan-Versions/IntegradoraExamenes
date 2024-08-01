package mx.edu.utez.integradiratjuans.model;

import java.sql.Timestamp;

public class Calificacion {
    private int idCalificacion;
    private String matriculaAlumno;
    private int idExamen;
    private double calificacion;
    private Timestamp fecha;

    // Constructor por defecto
    public Calificacion() {}

    // Constructor con parámetros
    public Calificacion(int idCalificacion, String matriculaAlumno, int idExamen, double calificacion, Timestamp fecha) {
        this.idCalificacion = idCalificacion;
        this.matriculaAlumno = matriculaAlumno;
        this.idExamen = idExamen;
        this.calificacion = calificacion;
        this.fecha = fecha;
    }

    public Calificacion(String matriculaAlumno, int idExamen, double calificacion) {
        this.matriculaAlumno = matriculaAlumno;
        this.idExamen = idExamen;
        this.calificacion = calificacion;
    }

    // Getters y Setters
    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

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

    @Override
    public String toString() {
        return "Calificacion{" +
                "idCalificacion=" + idCalificacion +
                ", matriculaAlumno='" + matriculaAlumno + '\'' +
                ", idExamen=" + idExamen +
                ", calificacion=" + calificacion +
                ", fecha=" + fecha +
                '}';
    }
}
