package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.dao.AdministradorDao;

import java.io.IOException;

@WebServlet("/Admin/cambiarContraAdmin")
public class CambiarContraAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la sesión del administrador
        HttpSession session = request.getSession();

        // Obtener la identificación del administrador desde la sesión
        String matricula = (String) session.getAttribute("matriculaAdmin");

        // Obtener la nueva contraseña desde el formulario enviado
        String nuevaContraseña = request.getParameter("nuevaContraseña");

        // Mostrar los valores para verificar que los datos están correctos
        System.out.println(matricula);
        System.out.println(nuevaContraseña);

        // Instanciar el DAO de Administrador
        AdministradorDao dao = new AdministradorDao();

        // Llamar al método de actualización de contraseña
        boolean update = dao.updateContra(matricula, nuevaContraseña);

        // Redireccionar dependiendo de si la actualización fue exitosa o no
        if (update) {
            response.sendRedirect("indexAdmin.jsp");  // Si la actualización fue exitosa
        } else {
            response.sendRedirect("error.jsp");  // Si hubo un error en la actualización
        }
    }
}