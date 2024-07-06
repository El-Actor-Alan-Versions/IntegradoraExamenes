<%@ page import="mx.edu.utez.integradiratjuans.dao.ExamenDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Examen" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestionar Exámenes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Gestionar Exámenes</h2>
    <table id="example" class="table table-striped table-hover" style="width: 100%">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Fecha de Aplicación</th>
            <th>Fecha de Cierre</th>
            <th>Clase</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            ExamenDao dao = new ExamenDao();
            ArrayList<Examen> lista = (ArrayList<Examen>) dao.getAll();
            for(Examen examen : lista) {
        %>
        <tr>
            <td><%= examen.getId_examen() %></td>
            <td><%= examen.getNombre() %></td>
            <td><%= examen.getFecha_aplicacion() %></td>
            <td><%= examen.getFecha_cierre() %></td>
            <td><%= examen.getId_clase() %></td>
            <td>
                <a href="actualizarExamen?id=<%= examen.getId_examen() %>">Actualizar</a> |
                <button type="button" class="btn btn-danger" onclick="confirmarEliminacion(<%= examen.getId_examen() %>)">Eliminar</button>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function confirmarEliminacion(id) {
        if (confirm('¿Estás seguro de que deseas eliminar este examen?')) {
            window.location.href = 'eliminarExamen?id=' + id;
        }
    }
</script>
</body>
</html>
