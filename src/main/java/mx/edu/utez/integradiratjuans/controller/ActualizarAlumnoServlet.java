package mx.edu.utez.integradiratjuans.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.AlumnoDao;
import mx.edu.utez.integradiratjuans.model.Alumno;
import java.io.IOException;

@WebServlet("/Admin/ActualizarAlumnoServlet")
public class ActualizarAlumnoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String correo = request.getParameter("correo");
        String estado = request.getParameter("estado");
        String contraseña = request.getParameter("contraseña");
        int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));

        Alumno alumno = new Alumno(matricula, nombre, apellidoPaterno, apellidoMaterno, correo, estado, contraseña, idGrupo);
        AlumnoDao dao = new AlumnoDao();

        boolean update = dao.update(alumno);

        if (update) {
            response.sendRedirect("verAlumnos.jsp" );
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}


