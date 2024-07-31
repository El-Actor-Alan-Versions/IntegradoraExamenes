<%@ page import="mx.edu.utez.integradiratjuans.dao.MateriaDao" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Materia" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Materias Registradas</title>
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
    <h2>Materias Registradas</h2>
    <button type="button" class="btn btn-info" onclick="location.href='registrarMateria.jsp'">Nueva Materia</button>
    <table id="example" class="table table-striped table-hover" style="width: 100%">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre de Materia</th>
            <th>Actualizar</th> <!-- Añadido el encabezado para la columna de actualización -->
            <th>Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                MateriaDao dao = new MateriaDao();
                List<Materia> lista = dao.getAll();
                for (Materia m : lista) {
        %>
        <tr>
            <td><%= m.getIdMateria() %></td>
            <td><%= m.getNombreMateria() %></td>
            <td>
                <a href="registrarMateria.jsp?idMateria=<%=m.getIdMateria() %>">Actualizar</a>
            </td>
            <td>
                <form method="post" action="eliminarMateriaServlet">
                    <input type="hidden" name="idMateria" value="<%= m.getIdMateria() %>">
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
        return confirm('¿Estás seguro de que deseas eliminar esta materia?');
    }
</script>
</body>
</html>
