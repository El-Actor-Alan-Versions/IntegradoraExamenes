<%@ page import="mx.edu.utez.integradiratjuans.dao.GrupoDao" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Grupo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Grupos Registrados</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/docente.css">
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
    <h2>Grupos Registrados</h2>
    <button type="button" class="btn btn-info" onclick="location.href='registrarGrupo.jsp'">Nuevo grupo</button>
    <table id="example" class="table table-striped table-hover" style="width: 100%">
        <thead>
        <tr>
            <th>ID Grupo</th>
            <th>Grado Grupo</th>
            <th>Carrera</th> <!-- Cambiado para mostrar el nombre de la carrera -->
            <th>Actualizar</th>
            <th>Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                GrupoDao dao = new GrupoDao();
                List<Grupo> lista = dao.getAll();
                for (Grupo g : lista) {
        %>
        <tr>
            <td><%= g.getIdGrupo() %></td>
            <td><%= g.getGradoGrupo() %></td>
            <td><%= g.getNombreCarrera() %></td> <!-- Mostrar el nombre de la carrera -->
            <td>
                <a href="registrarGrupo.jsp?idGrupo=<%= g.getIdGrupo() %>">Actualizar</a>
            </td>
            <td>
                <form method="post" action="eliminarGrupoServlet">
                    <input type="hidden" name="idGrupo" value="<%= g.getIdGrupo() %>">
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
    document.addEventListener('DOMContentLoaded', () => {
        const table = document.getElementById('example');
        new DataTable(table, {
            language: {
                url: '${pageContext.request.contextPath}/JS/es-MX.json'
            }
        });
    });

    function confirmarEliminacion() {
        return confirm('¿Estás seguro de que deseas eliminar este grupo?');
    }
</script>
</body>
</html>
