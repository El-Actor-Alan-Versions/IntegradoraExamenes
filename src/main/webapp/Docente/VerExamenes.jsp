<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Examen" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ver Exámenes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Exámenes</h2>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Fecha de Aplicación</th>
            <th>Fecha de Cierre</th>
            <th>Clase</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Examen> examenes = (List<Examen>) request.getAttribute("examenes");
            if (examenes != null && !examenes.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (Examen examen : examenes) {
                    Timestamp fechaAplicacion = examen.getFecha_aplicacion();
                    Timestamp fechaCierre = examen.getFecha_cierre();
        %>
        <tr>
            <td><%= examen.getId_examen() %></td>
            <td><%= examen.getNombre() %></td>
            <td><%= fechaAplicacion != null ? sdf.format(fechaAplicacion) : "N/A" %></td>
            <td><%= fechaCierre != null ? sdf.format(fechaCierre) : "N/A" %></td>
            <td><%= examen.getId_clase() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5" class="text-center">No hay exámenes disponibles</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
