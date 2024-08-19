package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.dao.AdministradorDao;
import mx.edu.utez.integradiratjuans.dao.AlumnoDao;
import mx.edu.utez.integradiratjuans.dao.ClaseDao;
import mx.edu.utez.integradiratjuans.dao.DocenteDao;
import mx.edu.utez.integradiratjuans.model.Administrador;
import mx.edu.utez.integradiratjuans.model.Alumno;
import mx.edu.utez.integradiratjuans.model.Clase;
import mx.edu.utez.integradiratjuans.model.Docente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UsuarioServlet", value = "/login")
public class UsuarioServlet extends HttpServlet {

    private final AdministradorDao adminDao = new AdministradorDao();
    private final AlumnoDao alumnoDao = new AlumnoDao();
    private final DocenteDao docenteDao = new DocenteDao();
    private final ClaseDao claseDao = new ClaseDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreUsuario = req.getParameter("matricula");
        String contrasena = req.getParameter("contra");

        String ruta = "index.jsp"; // Página por defecto

        try {
            // Verificar credenciales para cada tipo de usuario
            Administrador admin = adminDao.getOne(nombreUsuario, contrasena);
            Alumno alumno = alumnoDao.getOne(nombreUsuario, contrasena);
            Docente docente = docenteDao.getOne(nombreUsuario, contrasena);

            HttpSession session = req.getSession();

            if (admin != null) {
                session.setAttribute("usuario", admin);
                session.setAttribute("tipoSesion", "admin");
                ruta = "Admin/indexAdmin.jsp";
            } else if (alumno != null) {
                session.setAttribute("usuario", alumno);
                session.setAttribute("matriculaAlumno", alumno.getMatricula());

                int idGrupo = claseDao.getGrupoByAlumno(alumno.getMatricula());
                int idClase = claseDao.getClasesByGrupo(idGrupo);
                session.setAttribute("clase", idClase);
                ruta = "Alumno/indexAlumno.jsp";
            } else if (docente != null) {
                session.setAttribute("usuario", docente);
                session.setAttribute("matriculaDocente", docente.getMatricula());

                List<Clase> clases = claseDao.getClasesByDocente(docente.getMatricula());
                session.setAttribute("clases", clases);

                ruta = "Docente/indexDocente.jsp";
            } else {
                session.setAttribute("mensaje", "Credenciales incorrectas");
<<<<<<< HEAD
                ruta = "login.jsp";
=======
                ruta = "index.jsp"; // Redirigir a la página de login
>>>>>>> 936558493f76784f9a34c7b925c7ef0838f34702
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.getSession().setAttribute("mensaje", "Error en la base de datos");
            ruta = "login.jsp";
        }

        resp.sendRedirect(ruta);
    }
}
