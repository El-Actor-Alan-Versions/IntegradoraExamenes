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
import mx.edu.utez.integradiratjuans.utils.HashingUtils;

import java.io.IOException;

@WebServlet(name = "UpdateContraServlet", value = "/updateContra")
public class UpdateContraServlet extends HttpServlet {
    private final AdministradorDao administradorDao = new AdministradorDao();
    private final DocenteDao docenteDao = new DocenteDao();
    private final AlumnoDao alumnoDao = new AlumnoDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Obtener nueva contraseña
        String nuevaContra = request.getParameter("contraseña");
        if (nuevaContra == null || nuevaContra.trim().isEmpty()) {
            request.getSession().setAttribute("mensaje", "La contraseña no puede estar vacía.");
            response.sendRedirect("recuperacion.jsp");
            return;
        }

        // Hash de la nueva contraseña
        String nuevaContraHash;
        try {
            nuevaContraHash = HashingUtils.hashPassword(nuevaContra);
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("mensaje", "Error al procesar la contraseña.");
            response.sendRedirect("recuperacion.jsp");
            return;
        }

        // 2. Obtener código
        String codigo = request.getParameter("codigo");

        // 3. Buscar al usuario en las tres tablas
        Administrador administrador = administradorDao.getByCodigoRecuperacion(codigo);
        Docente docente = docenteDao.getByCodigoRecuperacion(codigo);
        Alumno alumno = alumnoDao.getByCodigoRecuperacion(codigo);

        if (administrador == null && docente == null && alumno == null) {
            request.getSession().setAttribute("mensaje", "Código inválido.");
            response.sendRedirect("recuperacion.jsp");
            return;
        }

        boolean exito = false;

        // 4. Actualizar contraseña y código en la BD según el tipo de usuario
        if (administrador != null) {
            administrador.setContraseña(nuevaContraHash); // Contraseña hasheada
            administrador.setCodigo_Recuperacion(null); // Limpiar el código
            exito = administradorDao.updateContraseña(administrador);
        } else if (docente != null) {
            docente.setContraseña(nuevaContraHash); // Contraseña hasheada
            docente.setCodigo_Recuperacion(null); // Limpiar el código
            exito = docenteDao.updateContraseña(docente);
        } else if (alumno != null) {
            alumno.setContraseña(nuevaContraHash); // Contraseña hasheada
            alumno.setCodigo_Recuperacion(null); // Limpiar el código
            exito = alumnoDao.updateContraseña(alumno);
        }

        // 5. Verificar si la actualización fue exitosa
        if (exito) {
            try {
                String email = administrador != null ? administrador.getCorreo() :
                        docente != null ? docente.getCorreo() :
                                alumno.getCorreo();
                new GmailSender().sendMail(email, "Contraseña actualizada", "Su contraseña ha sido actualizada correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                request.getSession().setAttribute("mensaje", "Error al enviar el correo de confirmación.");
                response.sendRedirect("recuperacion.jsp");
                return;
            }
            response.sendRedirect("index.jsp");
        } else {
            request.getSession().setAttribute("mensaje", "Código inválido o error en la actualización.");
            response.sendRedirect("recuperacion.jsp");
        }
    }
}
