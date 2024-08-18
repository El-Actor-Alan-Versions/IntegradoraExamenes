package mx.edu.utez.integradiratjuans.model;

public class Administrador {
    private String matricula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String contraseña;
    private String Codigo_Recuperacion;

    public Administrador() {
    }

    public Administrador(String matricula, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contraseña, String Codigo_Recuperacion) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contraseña = contraseña;
        this.Codigo_Recuperacion = Codigo_Recuperacion;
    }

    public String getCodigo_Recuperacion() {
        return Codigo_Recuperacion;
    }

    public void setCodigo_Recuperacion(String Codigo_Recuperacion) {
        this.Codigo_Recuperacion = Codigo_Recuperacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}