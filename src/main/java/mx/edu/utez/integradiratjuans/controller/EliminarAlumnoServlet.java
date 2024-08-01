package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.AlumnoDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Admin/eliminarAlumnoServlet")
public class EliminarAlumnoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AlumnoDao alumnoDao = new AlumnoDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String nuevoEstado = "baja"; // El estado de baja

        try {
            alumnoDao.updateEstado(matricula, nuevoEstado);
            response.sendRedirect("verAlumnos.jsp"); // Redirige a la página de usuarios registrados
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirige a una página de error
        }
    }
}
