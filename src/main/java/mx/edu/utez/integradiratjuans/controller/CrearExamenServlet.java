package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.RequestDispatcher;
import mx.edu.utez.integradiratjuans.dao.ExamenDao;
import mx.edu.utez.integradiratjuans.model.Examen;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;


@WebServlet("/Docente/CrearExamenServlet")
public class CrearExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String fechaAplicacionStr = request.getParameter("fecha_aplicacion");
        String fechaCierreStr = request.getParameter("fecha_cierre");
        String idClaseStr = request.getParameter("id_clase");

        // Validar parámetros
        if (nombre == null || nombre.isEmpty() ||
                descripcion == null || descripcion.isEmpty() ||
                fechaAplicacionStr == null || fechaAplicacionStr.isEmpty() ||
                fechaCierreStr == null || fechaCierreStr.isEmpty() ||
                idClaseStr == null || idClaseStr.isEmpty()) {
            request.setAttribute("errorMessage", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("crearExamen.jsp").forward(request, response);
            return;
        }

        // Convertir las fechas a Timestamp
        Timestamp fechaAplicacion;
        Timestamp fechaCierre;
        int idClase;

        try {
            fechaAplicacion = Timestamp.valueOf(fechaAplicacionStr.replace("T", " ") + ":00");
            fechaCierre = Timestamp.valueOf(fechaCierreStr.replace("T", " ") + ":00");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Formato de fecha inválido.");
            request.getRequestDispatcher("crearExamen.jsp").forward(request, response);
            return;
        }

        try {
            idClase = Integer.parseInt(idClaseStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "ID de clase inválido.");
            request.getRequestDispatcher("crearExamen.jsp").forward(request, response);
            return;
        }

        // Crear el objeto Examen
        Examen examen = new Examen();
        examen.setNombre(nombre);
        examen.setDescripcion(descripcion);
        examen.setFecha_aplicacion(fechaAplicacion);
        examen.setFecha_cierre(fechaCierre);
        examen.setId_clase(idClase);

        // Insertar el examen en la base de datos
        ExamenDao examenDao = new ExamenDao();
        int idExamen = examenDao.insert(examen);

        if (idExamen != 0) {
            // Establecer el ID del examen en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("idExamen", idExamen);

            // Redirigir a la página de creación de preguntas
            response.sendRedirect("crearPreguntas.jsp");
        } else {
            request.setAttribute("errorMessage", "Error al insertar el examen en la base de datos.");
            request.getRequestDispatcher("crearExamen.jsp").forward(request, response);
        }
    }
}
