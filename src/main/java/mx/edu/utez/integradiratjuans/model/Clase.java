package mx.edu.utez.integradiratjuans.model;

public class Clase {
    private int id_clase;
    private int id_grupo;
    private int id_materia;
    private String id_docente;

    public Clase(int id_clase, int id_grupo, int id_materia, String id_docente) {
        this.id_clase = id_clase;
        this.id_grupo = id_grupo;
        this.id_materia = id_materia;
        this.id_docente = id_docente;
    }

    public void setId_clase(int id_clase) {
        this.id_clase = id_clase;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public void setId_docente(String id_docente) {
        this.id_docente = id_docente;
    }

    public int getId_clase() {
        return id_clase;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public int getId_materia() {
        return id_materia;
    }

    public String getId_docente() {
        return id_docente;
    }
// Constructor, getters y setters
}
