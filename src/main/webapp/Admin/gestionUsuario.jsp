<%@ page import="mx.edu.utez.integradiratjuans.dao.MateriaDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Materia" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Materias Registradas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables.css">
</head>
<body>
<table id="example" class="table table-striped table-hover" style="width: 100%">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <%
        MateriaDao dao = new MateriaDao();
        ArrayList<Materia> lista = (ArrayList<Materia>) dao.getAll();
        for(Materia materia : lista) {
    %>
    <tr>
        <td><%= materia.getId_materia() %></td>
        <td><%= materia.getNombre_materia() %></td>
        <td>
            <a href="actualizarMateria?id=<%= materia.getId_materia() %>">Actualizar</a> |
            <button type="button" class="btn btn-danger" onclick="confirmarEliminacion(<%= materia.getId_materia() %>)">Eliminar</button>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<script src="${pageContext.request.contextPath}/JS/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
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

    function confirmarEliminacion(id) {
        if (confirm('¿Estás seguro de que deseas eliminar esta materia?')) {
            window.location.href = 'eliminarMateria?id=' + id;
        }
    }
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
