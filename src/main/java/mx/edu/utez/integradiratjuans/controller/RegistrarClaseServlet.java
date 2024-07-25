package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.ClaseDao;
import mx.edu.utez.integradiratjuans.model.Clase;


import java.io.IOException;

@WebServlet("/Admin/registrarClaseServlet")
public class RegistrarClaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Obtener parámetros del formulario
            int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
            int idMateria = Integer.parseInt(request.getParameter("idMateria"));
            String idDocente = request.getParameter("idDocente");

            // Crear el objeto Clase
            Clase clase = new Clase();
            clase.setId_grupo(idGrupo);
            clase.setId_materia(idMateria);
            clase.setMatricula(idDocente);

            // Instanciar el DAO
            ClaseDao claseDao = new ClaseDao();

            // Insertar la clase en la base de datos
            claseDao.insert(clase);

            // Redirigir a una página de éxito
            response.sendRedirect("exito.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
