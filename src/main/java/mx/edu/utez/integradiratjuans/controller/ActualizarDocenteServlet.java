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

@WebServlet("/Admin/actualizarDocenteServlet")
public class ActualizarDocenteServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String correo = request.getParameter("correo");
        String contraseña = request.getParameter("contraseña");
        String estado = request.getParameter("estado");

        Docente docente = new Docente(matricula, nombre, apellidoPaterno, apellidoMaterno, correo, contraseña, estado);
        DocenteDao docenteDao = new DocenteDao();

        boolean update = docenteDao.update(docente);

        if (update) {
            response.sendRedirect("verDocente.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
