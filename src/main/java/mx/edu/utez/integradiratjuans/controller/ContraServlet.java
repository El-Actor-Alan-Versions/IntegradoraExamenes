package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.AdministradorDao;
import mx.edu.utez.integradiratjuans.dao.DocenteDao;
import mx.edu.utez.integradiratjuans.dao.AlumnoDao;
import mx.edu.utez.integradiratjuans.model.Administrador;
import mx.edu.utez.integradiratjuans.model.Docente;
import mx.edu.utez.integradiratjuans.model.Alumno;
import mx.edu.utez.integradiratjuans.utils.GmailSender;
import mx.edu.utez.integradiratjuans.utils.SimpleRandomStringGenerator;

import java.io.IOException;

@WebServlet(name = "ContraServlet", value = "/recuContra")
public class ContraServlet extends HttpServlet {
    private final AdministradorDao administradorDao = new AdministradorDao();
    private final DocenteDao docenteDao = new DocenteDao();
    private final AlumnoDao alumnoDao = new AlumnoDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Obtener email
        String email = request.getParameter("correo");

        // 2. Revisar que existe en alguna de las tres tablas
        Administrador administrador = administradorDao.getByEmail(email);
        Docente docente = docenteDao.getByEmail(email);
        Alumno alumno = alumnoDao.getByEmail(email);

        if (administrador == null && docente == null && alumno == null) {
            // Manejo del caso en que el usuario no se encuentra
            request.getSession().setAttribute("mensaje", "Usuario no encontrado.");
            response.sendRedirect("recuperacion.jsp");
            return;
        }

        // 3. Generar código
        String codigo = SimpleRandomStringGenerator.generateRandomString(20);
        boolean exito = false;

        // 4. Insertar código en la BD correspondiente
        if (administrador != null) {
            administrador.setCodigo_Recuperacion(codigo);
            exito = administradorDao.updateCodigoRecuperacion(administrador);
        } else if (docente != null) {
            docente.setCodigo_Recuperacion(codigo);
            exito = docenteDao.updateCodigoRecuperacion(docente);
        } else if (alumno != null) {
            alumno.setCodigo_Recuperacion(codigo);
            exito = alumnoDao.updateCodigoRecuperacion(alumno);
        }

        if (exito) {
            System.out.println("Código de recuperación actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar el código de recuperación.");
        }

        // 5. Generar correo electrónico con enlace
        String enlace = "http://localhost:8080/practica3e_war_exploded/cambiocontra.jsp?codigo=" + codigo;
        String mensaje = "<p>Para cambiar tu contraseña, haz click en el siguiente enlace:</p>" +
                "<p><a href=\"" + enlace + "\">haz click aquí para cambiar tu contraseña</a></p>" +
                "<p>Si no solicitaste este cambio, por favor ignora este correo.</p>";
        try {
            new GmailSender().sendMail(email, "Recuperación de Contraseña Sistema de Exámenes", mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Obtener código
        String codigo = request.getParameter("codigo");

        // 2. Revisar que existe en alguna de las tres tablas
        Administrador administrador = administradorDao.getByCodigoRecuperacion(codigo);
        Docente docente = docenteDao.getByCodigoRecuperacion(codigo);
        Alumno alumno = alumnoDao.getByCodigoRecuperacion(codigo);

        if (administrador == null && docente == null && alumno == null) {
            request.getSession().setAttribute("mensaje", "Código inválido.");
            response.sendRedirect("recuperacion.jsp");
            return;
        }

        // 3. Redirigir a recuperacion.jsp con el código
        response.sendRedirect("recuperacion.jsp?codigo=" + codigo);
    }
}