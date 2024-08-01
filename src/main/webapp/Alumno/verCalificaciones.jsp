<%@ page import="mx.edu.utez.integradiratjuans.dao.CalificacionDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Calificacion" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.mail.Session" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calificaciones</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/usuarios.css">
</head>
<body>
<div id="navbar"></div>
<script>
    fetch('navbar.jsp')
        .then(response => response.text())
        .then(data => {
            document.getElementById('navbar').innerHTML = data;
        });
</script>

<div class="container mt-4">
    <h2>Calificaciones</h2>
    <table id="example" class="table table-striped table-hover" style="width: 100%">
        <thead>
        <tr>
            <th>ID Calificación</th>
            <th>Matrícula Alumno</th>
            <th>Nombre Examen</th>
            <th>Calificación</th>
            <th>Fecha</th>
        </tr>
        </thead>
        <tbody>
        <%
            String matriculaAlumno = (String) session.getAttribute("matriculaAlumno");
            try {
                CalificacionDao dao = new CalificacionDao();
                List<Calificacion> calificaciones = dao.getCalificacionesConNombreExamen(matriculaAlumno);
                for (Calificacion c : calificaciones) {
        %>
        <tr>
            <td><%= c.getIdCalificacion() %></td>
            <td><%= c.getMatriculaAlumno() %></td>
            <td><%= c.getNombreExamen() %></td>
            <td><%= c.getCalificacion() %></td>
            <td><%= c.getFecha() %></td>
        </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
            }
        %>
        </tbody>
    </table>
</div>


<script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath}/JS/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/JS/datatables.js"></script>
<script src="${pageContext.request.contextPath}/JS/dataTables.bootstrap5.js"></script>
<script src="${pageContext.request.contextPath}/JS/es-MX.json"></script>
<script>
    // Inicializar DataTables en la tabla objetivo
    document.addEventListener('DOMContentLoaded', () => {
        const table = document.getElementById('example');
        new DataTable(table, {
            language: {
                url: '${pageContext.request.contextPath}/JS/es-MX.json'
            }
        });
    });
</script>
<div class="text-center">
    <a href="indexAlumno.jsp" class="btn btn-primary mt-5">Regresar a Inicio</a>
</div>

</body>
</html>
