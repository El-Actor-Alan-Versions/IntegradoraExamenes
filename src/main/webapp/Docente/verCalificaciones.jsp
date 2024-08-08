<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Calificacion" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.CalificacionDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.ExamenDao" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calificaciones por Docente</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Calificaciones de los Alumnos</h1>

    <%
        String matriculaDocente = (String) session.getAttribute("matriculaDocente");
        if (matriculaDocente != null) {
            CalificacionDao calificacionDao = new CalificacionDao();
            ExamenDao examenDao = new ExamenDao();
            List<Calificacion> calificaciones = calificacionDao.obtenerCalificacionesPorDocente(matriculaDocente);
    %>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID Calificación</th>
            <th>Matrícula Alumno</th>
            <th>Examen</th>
            <th>Calificación</th>
            <th>Fecha</th>
            <th>Acciones</th> <!-- Nueva columna para acciones -->
        </tr>
        </thead>
        <tbody>
        <%
            if (calificaciones != null && !calificaciones.isEmpty()) {
                for (Calificacion calificacion : calificaciones) {
        %>
        <tr>
            <td><%= calificacion.getIdCalificacion() %></td>
            <td><%= calificacion.getMatriculaAlumno() %></td>
            <td><%= examenDao.obtenerNombreExamenPorId(calificacion.getIdExamen()) %></td>
            <td><%= calificacion.getCalificacion() %></td>
            <td><%= calificacion.getFecha() %></td>
            <td>
                <form action="CalificarServlet" method="get">
                    <input type="hidden" name="idCalificacion" value="<%= calificacion.getIdCalificacion() %>">
                    <button type="submit" class="btn btn-primary">Calificar</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">No hay calificaciones disponibles.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <%
    } else {
    %>
    <p>Error: No se encontró la matrícula del docente en la sesión.</p>
    <%
        }
    %>
</div>
</body>
</html>
