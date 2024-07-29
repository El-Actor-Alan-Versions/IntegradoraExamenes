package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.AlumnoDao;
import mx.edu.utez.integradiratjuans.model.Alumno;

import java.io.IOException;

@WebServlet("/Admin/actualizarAlumnoServlet")
public class ActualizarAlumnoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String correo = request.getParameter("correo");
        String contraseña = request.getParameter("contraseña");
        String estado = request.getParameter("estado");
        int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
        String nombreGrupo = request.getParameter("nombreGrupo");

        Alumno alumno = new Alumno(matricula, nombre, apellidoPaterno, apellidoMaterno, correo, contraseña, idGrupo, estado, nombreGrupo);
        AlumnoDao alumnoDao = new AlumnoDao();

        boolean update = alumnoDao.update(alumno);

        if (update) {
            response.sendRedirect("verAlumno.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
