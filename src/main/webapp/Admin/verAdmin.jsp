<%@ page import="mx.edu.utez.integradiratjuans.dao.AdministradorDao" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Administrador" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Administradores Registrados</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables.css">
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
    <h2>Administradores Registrados</h2>
    <button type="button" class="btn btn-info" onclick="location.href='registrarAdmin.jsp'">Nuevo administrador</button>
    <table id="example" class="table table-striped table-hover" style="width: 100%">
        <thead>
        <tr>
            <th>Matrícula</th>
            <th>Nombre</th>
            <th>Apellido Paterno</th>
            <th>Apellido Materno</th>
            <th>Correo</th>
            <th>Actualizar</th>
            <th>Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                AdministradorDao dao = new AdministradorDao();
                List<Administrador> lista = dao.getAll();
                for (Administrador a : lista) {
        %>
        <tr>
            <td><%= a.getMatricula() %></td>
            <td><%= a.getNombre() %></td>
            <td><%= a.getApellidoPaterno() %></td>
            <td><%= a.getApellidoMaterno() %></td>
            <td><%= a.getCorreo() %></td>
            <td>
                <a href="registrarAdmin.jsp?matricula=<%= a.getMatricula() %>">Actualizar</a>
            </td>
            <td>
                <form method="post" action="eliminarAdministradorServlet">
                    <input type="hidden" name="matricula" value="<%= a.getMatricula() %>">
                    <button type="submit" class="btn btn-danger" onclick="return confirmarEliminacion()">Eliminar</button>
                </form>
            </td>
        </tr>
        <%
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
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

    function confirmarEliminacion() {
        return confirm('¿Estás seguro de que deseas dar de baja a este administrador?');
    }
</script>
</body>
</html>
