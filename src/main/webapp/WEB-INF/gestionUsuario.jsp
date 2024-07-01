<%@ page import="mx.edu.utez.practica3e.dao.UsuarioDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mx.edu.utez.practica3e.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Usuarios Registrados</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/datatables.css">
</head>
<body>
<table id="example" class="table table-striped table-hover" style="width: 100%">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Correo</th>
        <th>Tipo</th>
        <th>Estado</th>
        <th>Actualizar</th>
        <th>Eliminar</th>
    </tr>
    </thead>
    <tbody>
    <%
        UsuarioDao dao = new UsuarioDao();
        ArrayList<Usuario> lista = dao.getAll();
        for(Usuario u : lista) {
    %>
    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getNombre_usuario() %></td>
        <td><%= u.getCorreo() %></td>
        <td><%= (u.getTipo_usuario() == 1) ? "Admin" : "Normal" %></td>
        <td><%= (u.isEstado()) ? "Activo" : "Inactivo" %></td>
        <td><a href="login?id=<%= u.getId() %>">Actualizar</a></td>
        <td>
            <form id="formEliminar<%= u.getId() %>" method="post" action="updateUsuarioEstado">
                <input type="hidden" name="id" value="<%= u.getId() %>">
                <button type="button" class="btn btn-danger" onclick="confirmarEliminacion(<%= u.getId() %>)">Eliminar</button>
            </form>
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
        if (confirm('¿Estás seguro de que deseas eliminar este usuario?')) {
            document.getElementById('formEliminar' + id).submit();
        }
    }
</script>
</body>
</html>
