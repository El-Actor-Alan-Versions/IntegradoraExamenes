package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.ExamenDao;
import mx.edu.utez.integradiratjuans.model.Examen;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/registrarExamen")
public class RegistrarExamenServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String fechaAplicacionStr = request.getParameter("fecha_aplicacion");
        String fechaCierreStr = request.getParameter("fecha_cierre");
        int idClase = Integer.parseInt(request.getParameter("id_clase"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaAplicacion = null;
        Date fechaCierre = null;
        try {
            fechaAplicacion = sdf.parse(fechaAplicacionStr);
            fechaCierre = sdf.parse(fechaCierreStr);
        } catch (ParseException e) {
            e.printStackTrace();
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
