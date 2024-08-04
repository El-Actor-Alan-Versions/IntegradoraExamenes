package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.AdministradorDao;
import mx.edu.utez.integradiratjuans.model.Administrador;


import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/Admin/RegistrarAdministradorServlet")
public class RegistrarAdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String correo = matricula + "@utez.edu.mx";
        String contraseña = matricula;

        Administrador admin = new Administrador();
        admin.setMatricula(matricula);
        admin.setNombre(nombre);
        admin.setApellidoPaterno(apellidoPaterno);
        admin.setApellidoMaterno(apellidoMaterno);
        admin.setCorreo(correo);
        admin.setContraseña(contraseña);

        AdministradorDao administradorDao = new AdministradorDao();

        boolean inserted = administradorDao.insert(admin);

        if (inserted){
            response.sendRedirect("verAdmin.jsp");
        } else {
            response.sendRedirect("erro.jsp");
        }

    }
}
