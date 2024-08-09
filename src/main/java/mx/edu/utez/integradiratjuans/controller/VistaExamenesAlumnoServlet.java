    package mx.edu.utez.integradiratjuans.controller;

    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import jakarta.servlet.http.HttpSession;
    import mx.edu.utez.integradiratjuans.dao.ClaseDao;
    import mx.edu.utez.integradiratjuans.dao.ExamenDao;
    import mx.edu.utez.integradiratjuans.model.Clase;
    import mx.edu.utez.integradiratjuans.model.Examen;

    import java.io.IOException;
    import java.sql.Timestamp;
    import java.util.ArrayList;
    import java.util.List;



    @WebServlet("/Alumno/VerExamenesAlumnoServlet")
    public class VistaExamenesAlumnoServlet extends HttpServlet {


        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Obtener el ID de la clase y matrícula desde la sesión
            HttpSession session = request.getSession();
            Integer idClase = (Integer) session.getAttribute("clase");
            String matricula = (String) session.getAttribute("matriculaAlumno");

            // Verificar que los datos de la sesión no sean nulos
            if (idClase == null || matricula == null) {
                request.setAttribute("errorMessage", "No se encontró información de sesión válida.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            // Consultar los exámenes que el alumno no ha realizado aún
            ExamenDao examenDao = new ExamenDao();
            List<Examen> examenes = examenDao.getExamenesNoRealizadosPorAlumno(idClase, matricula);

            // Verificar si se obtuvieron exámenes
            if (examenes == null || examenes.isEmpty()) {
                request.setAttribute("mensaje", "No hay exámenes disponibles.");
            } else {
                request.setAttribute("examenes", examenes);
            }

            // Redirigir a la página JSP para mostrar los exámenes
            request.getRequestDispatcher("verExamenes.jsp").forward(request, response);
        }



    }
