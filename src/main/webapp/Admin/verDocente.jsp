<%@ page import="mx.edu.utez.integradiratjuans.dao.DocenteDao" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Docente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Docentes Registrados</title>
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
    <h2>Docentes Registrados</h2>
    <button type="button" class="btn btn-info" onclick="location.href='registrarDocente.jsp'">Nuevo docente</button>
    <table id="example" class="table table-striped table-hover" style="width: 100%">
        <thead>
        <tr>
            <th>Matrícula</th>
            <th>Nombre</th>
            <th>Apellido Paterno</th>
            <th>Apellido Materno</th>
            <th>Correo</th>
            <th>Estado</th>
            <th>Actualizar</th>
            <th>Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                DocenteDao dao = new DocenteDao();
                List<Docente> lista = dao.getAll();
                for (Docente d : lista) {
        %>
        <tr>
            <td><%= d.getMatricula() %></td>
            <td><%= d.getNombre() %></td>
            <td><%= d.getApellidoPaterno() %></td>
            <td><%= d.getApellidoMaterno() %></td>
            <td><%= d.getCorreo() %></td>
            <td><%= d.getEstado() %></td>
            <td>

                <a href="registrarDocente.jsp?matricula=<%= d.getMatricula() %>">Actualizar</a>
            </td>
            <td>
                <form method="post" action="eliminarDocenteServlet">
                    <input type="hidden" name="matricula" value="<%= d.getMatricula() %>">
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
        return confirm('¿Estás seguro de que deseas dar de baja a este docente?');
    }
</script>
<div class="text-center">
    <a href="indexAdmin.jsp" class="btn btn-primary mt-5">Regresar a Inicio</a>
</div>
</body>
</html>
