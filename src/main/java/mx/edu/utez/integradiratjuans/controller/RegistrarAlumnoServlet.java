package mx.edu.utez.integradiratjuans.controller;

import mx.edu.utez.integradiratjuans.dao.AlumnoDao;
import mx.edu.utez.integradiratjuans.model.Alumno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.AlumnoDao;
import mx.edu.utez.integradiratjuans.model.Alumno;
import java.io.IOException;

@WebServlet("/Admin/RegistrarAlumnoServlet")
public class RegistrarAlumnoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matriculaInsert");
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String correo;
        String estado; // Asignar valor por defecto o desde el formulario si es necesario
        String contraseña = request.getParameter("matriculaInsert");
        String idGrupoStr = request.getParameter("idGrupo");

        correo = matricula + "@utez.edu.mx";
        estado = "Activo";


        // Validación y conversión de idGrupo
        int idGrupo = 0;
        try {
            idGrupo = Integer.parseInt(idGrupoStr);
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir idGrupo a int: " + e.getMessage());
            response.sendRedirect("error.jsp?mensaje=Error al convertir ID del grupo");
            return;
        }

        // Comprobación de valor nulo o vacío
        if (matricula == null || matricula.isEmpty()) {
            response.sendRedirect("error.jsp?mensaje=Matrícula no puede ser nula o vacía");
            return;
        }

        Alumno alumno = new Alumno(matricula, nombre, apellidoPaterno, apellidoMaterno, correo, contraseña, idGrupo, estado);
        System.out.println(alumno.getMatricula());
        System.out.println(alumno.getNombre());

        // Inserción en la base de datos
        AlumnoDao dao = new AlumnoDao();
        try {
            dao.insert(alumno);
            response.sendRedirect("verAlumnos.jsp?mensaje=Alumno registrado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?mensaje=Error al registrar alumno");
        }
    }
}
