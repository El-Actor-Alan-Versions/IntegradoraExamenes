package mx.edu.utez.integradiratjuans.controller;

import mx.edu.utez.integradiratjuans.dao.AlumnoDao;
import mx.edu.utez.integradiratjuans.model.Alumno;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.AlumnoDao;
import mx.edu.utez.integradiratjuans.model.Alumno;
import java.io.IOException;

@WebServlet("/ActualizarAlumnoServlet")
public class ActualizarAlumnoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String correo = request.getParameter("correo");
        String estado = request.getParameter("estado");
        String contraseña = request.getParameter("contraseña");
        int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));

        Alumno alumno = new Alumno(matricula, nombre, apellidoPaterno, apellidoMaterno, correo, estado, contraseña, idGrupo);

        AlumnoDao dao = new AlumnoDao();
        try {
            dao.update(alumno);
            response.sendRedirect("verAlumnos.jsp?mensaje=Alumno actualizado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?mensaje=Error al actualizar alumno");
        }
    }
}


