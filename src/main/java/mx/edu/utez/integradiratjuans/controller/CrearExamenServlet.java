package mx.edu.utez.integradiratjuans.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.ExamenDao;
import mx.edu.utez.integradiratjuans.model.Examen;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/Docente/crearExamen")
public class CrearExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String fechaAplicacionStr = request.getParameter("fecha_aplicacion");
        String fechaCierreStr = request.getParameter("fecha_cierre");
        int idClase = Integer.parseInt(request.getParameter("id_clase"));

        // Definir formato de fecha y hora
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Timestamp fechaAplicacion = null;
        Timestamp fechaCierre = null;
        try {
            java.util.Date utilFechaAplicacion = sdf.parse(fechaAplicacionStr);
            java.util.Date utilFechaCierre = sdf.parse(fechaCierreStr);
            fechaAplicacion = new Timestamp(utilFechaAplicacion.getTime());
            fechaCierre = new Timestamp(utilFechaCierre.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
            return;
        }

        Examen examen = new Examen();
        examen.setNombre(nombre);
        examen.setFecha_aplicacion(fechaAplicacion);
        examen.setFecha_cierre(fechaCierre);
        examen.setId_clase(idClase);

        ExamenDao dao = new ExamenDao();
        if (dao.insert(examen)) {
            response.sendRedirect("exito.html");
        } else {
            response.sendRedirect("error.html");
        }
    }
}
