package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.dao.AlumnoDao;

import java.io.IOException;

@WebServlet("/Alumno/cambiarContraAlumno")
public class CambiarContrAlumn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String matricula = (String) session.getAttribute("matriculaAlumno");
        String nuevaContraseña = request.getParameter("nuevaContraseña");

        System.out.println(matricula);
        System.out.println(nuevaContraseña);

        AlumnoDao dao = new AlumnoDao();
        boolean update = dao.updateContra(matricula, nuevaContraseña);

        if (update) {
            response.sendRedirect("indexAlumno.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
