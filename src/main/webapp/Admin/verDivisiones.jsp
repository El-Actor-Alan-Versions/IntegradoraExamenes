<%@ page import="mx.edu.utez.integradiratjuans.dao.DivisionDao" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Division" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Divisiones Registradas</title>
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
    <h2>Divisiones Registradas</h2>
    <button type="button" class="btn btn-info" onclick="location.href='registrarDivision.jsp'">Nueva División</button>
    <table id="example" class="table table-striped table-hover" style="width: 100%">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre de División</th>
            <th>Actualizar</th>
            <th>Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                DivisionDao dao = new DivisionDao();
                List<Division> lista = dao.getAll();
                for (Division d : lista) {
        %>
        <tr>
            <td><%= d.getIdDivision() %></td>
            <td><%= d.getNombreDivision() %></td>
            <td>
                <a href="registrarDivision.jsp?idDivision=<%= d.getIdDivision() %>" class="btn btn-warning">Actualizar</a>
            </td>
            <td>
                <form method="post" action="eliminarDivisionServlet" onsubmit="return confirmarEliminacion()">
                    <input type="hidden" name="idDivision" value="<%= d.getIdDivision() %>">
                    <button type="submit" class="btn btn-danger">Eliminar</button>
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
        return confirm('¿Estás seguro de que deseas eliminar esta división?');
    }
</script>
</body>
</html>
