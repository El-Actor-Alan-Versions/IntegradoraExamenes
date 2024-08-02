package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.DocenteDao;
import mx.edu.utez.integradiratjuans.model.Docente;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Admin/registrarDocenteServlet")
public class RegistrarDocenteServlet extends HttpServlet {

    private final DocenteDao docenteDao = new DocenteDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String correo = matricula + "@utez.edu.mx";
        String contraseña = matricula;
        String estado = "Activo";

        Docente docente = new Docente();
        docente.setMatricula(matricula);
        docente.setNombre(nombre);
        docente.setApellidoPaterno(apellidoPaterno);
        docente.setApellidoMaterno(apellidoMaterno);
        docente.setCorreo(correo);
        docente.setContraseña(contraseña);
        docente.setEstado(estado);

        try {
            docenteDao.insert(docente);
            response.sendRedirect("verDocente.jsp");
        } catch (SQLException e) {
            e.printStackTrace(); // Para debugging en la consola del servidor
            request.setAttribute("errorMessage", "Error al registrar el docente: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response); // Mostrar error en la página
        }
    }
}
