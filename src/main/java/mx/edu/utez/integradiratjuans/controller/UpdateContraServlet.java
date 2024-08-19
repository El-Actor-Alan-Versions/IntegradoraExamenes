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

import java.io.IOException;

@WebServlet(name = "UpdateContraServlet", value = "/updateContra")
public class UpdateContraServlet extends HttpServlet {
    private final AdministradorDao administradorDao = new AdministradorDao();
    private final DocenteDao docenteDao = new DocenteDao();
    private final AlumnoDao alumnoDao = new AlumnoDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nuevaContra = request.getParameter("contraseña");
        if (nuevaContra == null || nuevaContra.trim().isEmpty()) {
            request.getSession().setAttribute("mensaje", "La contraseña no puede estar vacía.");
            response.sendRedirect("recuperacion.jsp");
            return;
        }

        String codigo = request.getParameter("codigo");
        if (codigo == null || codigo.trim().isEmpty()) {
            request.getSession().setAttribute("mensaje", "El código de recuperación no puede estar vacío.");
            response.sendRedirect("recuperacion.jsp");
            return;
        }

        Administrador administrador = administradorDao.getByCodigoRecuperacion(codigo);
        Docente docente = docenteDao.getByCodigoRecuperacion(codigo);
        Alumno alumno = alumnoDao.getByCodigoRecuperacion(codigo);

        if (administrador == null && docente == null && alumno == null) {
            request.getSession().setAttribute("mensaje", "Código inválido.");
            response.sendRedirect("recuperacion.jsp");
            return;
        }

        boolean exito = false;
        String email = null;

        if (administrador != null) {
            exito = administradorDao.updateContraseña(administrador.getMatricula(), nuevaContra);
            email = administrador.getCorreo();
        } else if (docente != null) {
            exito = docenteDao.updateContraseña(docente.getMatricula(), nuevaContra);
            email = docente.getCorreo();
        } else if (alumno != null) {
            exito = alumnoDao.updateContraseña(alumno.getMatricula(), nuevaContra);
            email = alumno.getCorreo();
        }

        if (exito) {
            try {
                new GmailSender().sendMail(email, "Contraseña actualizada", "Su contraseña ha sido actualizada correctamente.");
                request.getSession().setAttribute("mensaje", "La contraseña ha sido actualizada exitosamente.");
            } catch (Exception e) {
                e.printStackTrace();
                request.getSession().setAttribute("mensaje", "Error al enviar el correo de confirmación.");
            }
            response.sendRedirect("index.jsp");
        } else {
            request.getSession().setAttribute("mensaje", "Código inválido o error en la actualización.");
            response.sendRedirect("recuperacion.jsp");
        }
    }
}




