package mx.edu.utez.integradiratjuans.model;

public class ExamenAlumno {

    private int idEmanAlumno;
    private int idExamen;
    private boolean realizado = false;
    private String matriculaAlumno;


    public ExamenAlumno( int idExamen, String matriculaAlumno) {
        this.idExamen = idExamen;
        this.matriculaAlumno = matriculaAlumno;
    }

    public ExamenAlumno () {

    }

    public int getIdEmanAlumno() {
        return idEmanAlumno;
    }

    public void setIdEmanAlumno(int idEmanAlumno) {
        this.idEmanAlumno = idEmanAlumno;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public String getMatriculaAlumno() {
        return matriculaAlumno;
    }

    public void setMatriculaAlumno(String matriculaAlumno) {
        this.matriculaAlumno = matriculaAlumno;
    }
}
