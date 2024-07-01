package mx.edu.utez.integradiratjuans.model;

import java.io.Serializable;

public class Alumno implements Serializable {


    private String matricula;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String correo;
    private String contra;
    private Boolean estado;
    private int id_grupo; //Llave foranea de la tabla grupo


    public Alumno() {
    }

    public Alumno(String matricula, String nombre, String apellido_paterno, String apellido_materno, String correo, String contra, Boolean estado, int id_grupo) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.correo = correo;
        this.contra = contra;
        this.estado = estado;
        this.id_grupo = id_grupo;
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

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }
    public void setContra(String contra) {
        this.contra = contra;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }
}

