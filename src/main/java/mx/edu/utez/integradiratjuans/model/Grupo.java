package mx.edu.utez.integradiratjuans.model;

public class Grupo {
    private int id_grupo;
    private String grado_grupo;
    private int id_carrera;

    public Grupo() {
    }

    public Grupo(int id_grupo, String grado_grupo, int id_carrera) {
        this.id_grupo = id_grupo;
        this.grado_grupo = grado_grupo;
        this.id_carrera = id_carrera;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getGrado_grupo() {
        return grado_grupo;
    }

    public void setGrado_grupo(String grado_grupo) {
        this.grado_grupo = grado_grupo;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }
}

