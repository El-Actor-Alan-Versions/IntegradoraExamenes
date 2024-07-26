package mx.edu.utez.integradiratjuans.controller;

import mx.edu.utez.integradiratjuans.dao.ExamenDao;
import mx.edu.utez.integradiratjuans.dao.GrupoDao;
import mx.edu.utez.integradiratjuans.model.Examen;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/Docente/CrearExamenServlet")
public class CrearExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet llamado");

        // Recuperar los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String fechaAplicacionStr = request.getParameter("fecha_aplicacion");
        String fechaCierreStr = request.getParameter("fecha_cierre");
        String idGrupoStr = request.getParameter("idGrupo");

        // Depuración: Imprimir los parámetros recibidos
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha Aplicacion: " + fechaAplicacionStr);
        System.out.println("Fecha Cierre: " + fechaCierreStr);
        System.out.println("ID Grupo: " + idGrupoStr);

        // Validar parámetros
        if (nombre == null || nombre.isEmpty() ||
                fechaAplicacionStr == null || fechaAplicacionStr.isEmpty() ||
                fechaCierreStr == null || fechaCierreStr.isEmpty() ||
                idGrupoStr == null || idGrupoStr.isEmpty()) {
            request.setAttribute("errorMessage", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("crearExamen.jsp").forward(request, response);
            return;
        }

        // Convertir las fechas a Timestamp
        Timestamp fechaAplicacion;
        Timestamp fechaCierre;
        int idGrupo;

        try {
            fechaAplicacion = Timestamp.valueOf(fechaAplicacionStr.replace("T", " ") + ":00");
            fechaCierre = Timestamp.valueOf(fechaCierreStr.replace("T", " ") + ":00");
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Depuración: Imprimir traza de error
            request.setAttribute("errorMessage", "Formato de fecha inválido.");
            request.getRequestDispatcher("crearExamen.jsp").forward(request, response);
            return;
        }

        try {
            idGrupo = Integer.parseInt(idGrupoStr);
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Depuración: Imprimir traza de error
            request.setAttribute("errorMessage", "ID de grupo inválido.");
            request.getRequestDispatcher("crearExamen.jsp").forward(request, response);
            return;
        }

        // Obtener el id_clase correspondiente al id_grupo
        GrupoDao grupoDao = new GrupoDao();
        int idClase = grupoDao.getIdClaseByGrupoId(idGrupo);

        // Depuración: Imprimir el ID de la clase obtenido
        System.out.println("ID Clase obtenido: " + idClase);

        // Validar que idClase no sea inválido
        if (idClase == -1) {
            request.setAttribute("errorMessage", "Grupo no encontrado o sin clase asignada.");
            request.getRequestDispatcher("crearExamen.jsp").forward(request, response);
            return;
        }

        // Crear el objeto Examen
        Examen examen = new Examen();
        examen.setNombre(nombre);
        examen.setFecha_aplicacion(fechaAplicacion);
        examen.setFecha_cierre(fechaCierre);
        examen.setId_clase(idClase);

        // Depuración: Imprimir los datos del examen a insertar
        System.out.println("Examen a insertar:");
        System.out.println("Nombre: " + examen.getNombre());
        System.out.println("Fecha Aplicacion: " + examen.getFecha_aplicacion());
        System.out.println("Fecha Cierre: " + examen.getFecha_cierre());
        System.out.println("ID Clase: " + examen.getId_clase());

        // Insertar el examen en la base de datos
        ExamenDao examenDao = new ExamenDao();
        boolean success = examenDao.insert(examen);

        if (success) {
            response.sendRedirect("exito.jsp");
        } else {
            request.setAttribute("errorMessage", "Error al insertar el examen en la base de datos.");
            request.getRequestDispatcher("crearExamen.jsp").forward(request, response);
        }
    }
}
