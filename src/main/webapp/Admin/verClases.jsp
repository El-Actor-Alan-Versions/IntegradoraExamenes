<%@ page import="mx.edu.utez.integradiratjuans.dao.ClaseDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.GrupoDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.MateriaDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.DocenteDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Clase" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Grupo" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Materia" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Docente" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Clases Registradas</title>
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
    <h2>Clases Registradas</h2>
    <button type="button" class="btn btn-info" onclick="location.href='registrarClase.jsp'">Nueva clase</button>
    <table id="example" class="table table-striped table-hover" style="width: 100%">
        <thead>
        <tr>
            <th>ID Clase</th>
            <th>Grupo</th>
            <th>Materia</th>
            <th>Docente</th>
            <th>Actualizar</th>
            <th>Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                // Instanciar los DAOs
                ClaseDao claseDao = new ClaseDao();
                GrupoDao grupoDao = new GrupoDao();
                MateriaDao materiaDao = new MateriaDao();
                DocenteDao docenteDao = new DocenteDao();

                // Obtener las clases
                List<Clase> lista = claseDao.getAll();

                for (Clase c : lista) {
                    Grupo grupo = grupoDao.getById(c.getId_grupo());
                    Materia materia = materiaDao.getById(c.getId_materia());
                    Docente docente = docenteDao.getById(c.getMatricula());
        %>
        <tr>
            <td><%= c.getId_clase() %></td>
            <td><%= grupo.getGradoGrupo() %></td>
            <td><%= materia.getNombreMateria() %></td>
            <td><%= docente.getNombre() %> <%= docente.getApellidoPaterno() %> <%= docente.getApellidoMaterno() %></td>
            <td>
                <a href="registrarClase.jsp?id_clase=<%= c.getId_clase() %>">Actualizar</a>
            </td>
            <td>
                <form method="post" action="eliminarClaseServlet">
                    <input type="hidden" name="idClase" value="<%= c.getId_clase() %>">
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
        return confirm('¿Estás seguro de que deseas eliminar esta clase?');
    }
</script>
</body>
</html>
