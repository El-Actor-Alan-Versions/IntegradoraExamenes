package mx.edu.utez.integradiratjuans.model;

public class Grupo {
    private int idGrupo;
    private String gradoGrupo;
    private int idCarrera;

    public Grupo() {

    }
    public Grupo(int idGrupo, String gradoGrupo, int idCarrera) {
        this.idGrupo = idGrupo;
        this.gradoGrupo = gradoGrupo;
        this.idCarrera = idCarrera;
    }

    // Getters y setters
    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getGradoGrupo() {
        return gradoGrupo;
    }

    public void setGradoGrupo(String gradoGrupo) {
        this.gradoGrupo = gradoGrupo;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }
}
