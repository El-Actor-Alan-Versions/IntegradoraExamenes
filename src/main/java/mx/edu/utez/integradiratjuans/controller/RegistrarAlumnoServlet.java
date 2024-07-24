package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.AlumnoDao;
import mx.edu.utez.integradiratjuans.model.Alumno;


import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Admin/registrarAlumno")
public class RegistrarAlumnoServlet extends HttpServlet {

    private final AlumnoDao alumnoDao = new AlumnoDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String correo = request.getParameter("correo");
        String contraseña = request.getParameter("contraseña");
        int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
        String estado = request.getParameter("estado");

        Alumno alumno = new Alumno();
        alumno.setMatricula(matricula);
        alumno.setNombre(nombre);
        alumno.setApellidoPaterno(apellidoPaterno);
        alumno.setApellidoMaterno(apellidoMaterno);
        alumno.setCorreo(correo);
        alumno.setContraseña(contraseña);
        alumno.setIdGrupo(idGrupo);
        alumno.setEstado(estado);

        try {
            alumnoDao.insert(alumno);
            response.sendRedirect("exito.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
