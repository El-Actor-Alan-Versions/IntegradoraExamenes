package mx.edu.utez.integradiratjuans.model;

public class Clase {
    private int id_clase;
    private int id_grupo;
    private int id_materia;
    private String matricula;
    private Grupo grupo;
    private String nombre; // Asegúrate de que este campo exista
    private String gradoGrupo; // Añadir este campo


    public Clase() {

    }

    public Clase(int id_clase, int id_grupo, int id_materia, String matricula) {
        this.id_clase = id_clase;
        this.id_grupo = id_grupo;
        this.id_materia = id_materia;
        this.matricula = matricula;
    }

    // Getters y setters
    public int getId_clase() {
        return id_clase;
    }

    public void setId_clase(int id_clase) {
        this.id_clase = id_clase;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    public String getGradoGrupo() {
        return gradoGrupo;
    }

    public void setGradoGrupo(String gradoGrupo) {
        this.gradoGrupo = gradoGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
