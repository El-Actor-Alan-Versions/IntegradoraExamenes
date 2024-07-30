package mx.edu.utez.integradiratjuans.model;

public class Alumno {
    // Atributos
    private String matricula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String contraseña;
    private int idGrupo;
    private String estado = "activo"; // Valor predeterminado
    private String nombreGrupo; // Nuevo campo

    public Alumno(String matricula, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contraseña, int idGrupo, String estado, String nombreGrupo) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contraseña = contraseña;
        this.idGrupo = idGrupo;
        this.estado = estado;
        this.nombreGrupo = nombreGrupo;
    }

    // Constructor, getters y setters
    public Alumno() {
        // Otros campos inicializados aquí
    }

    public Alumno(String matricula, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String estado, String contraseña, int idGrupo) {
    }

    // Getters y setters
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public int getIdGrupo() { return idGrupo; }
    public void setIdGrupo(int idGrupo) { this.idGrupo = idGrupo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
}
