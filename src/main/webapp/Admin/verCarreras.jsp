<%@ page import="mx.edu.utez.integradiratjuans.dao.CarreraDao" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Carrera" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carreras Registradas</title>
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
    <h2>Carreras Registradas</h2>
    <button type="button" class="btn btn-info" onclick="location.href='registrarCarrera.jsp'">Nueva carrera</button>
    <table id="example" class="table table-striped table-hover" style="width: 100%">
        <thead>
        <tr>
            <th>ID Carrera</th>
            <th>Nombre de la Carrera</th>
            <th>ID División</th>
            <th>Actualizar</th>
            <th>Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                CarreraDao dao = new CarreraDao();
                List<Carrera> lista = dao.getAll();
                for (Carrera c : lista) {
        %>
        <tr>
            <td><%= c.getId_carrera() %></td>
            <td><%= c.getNombre_carrera() %></td>
            <td><%= c.getId_division() %></td>
            <td>
                <a href="registrarCarrera.jsp?id_carrera=<%= c.getId_carrera() %>">Actualizar</a>
            </td>
            <td>
                <form method="post" action="eliminarCarreraServlet">
                    <input type="hidden" name="id_carrera" value="<%= c.getId_carrera() %>">
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
        return confirm('¿Estás seguro de que deseas eliminar esta carrera?');
    }
</script>
</body>
</html>
