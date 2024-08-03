<%@ page import="mx.edu.utez.integradiratjuans.dao.AlumnoDao" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Alumno" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Alumnos Registrados</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vistas.css">
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

<div class="container">
    <div class="card">
        <div class="header-card">
            <img src="../img/crear.png" width="68">
            <h2>Gestión de Alumnos</h2>
            <button type="button" class="btn btn-new" onclick="location.href='registarAlumno.jsp'">Nuevo alumno</button>
            <button type="button" class="btn btn-conf" onclick="location.href='indexAdmin.jsp'">Volver</button>
        </div>
        <div class="card-body">
            <table id="example" class="table table-custom">
                <thead>
                <tr>
                    <th>Matrícula</th>
                    <th>Nombre</th>
                    <th>Apellido Paterno</th>
                    <th>Apellido Materno</th>
                    <th>Correo</th>
                    <th>Estado</th>
                    <th>Grupo</th>
                    <th>Actualizar</th>
                    <th>Eliminar</th>
                </tr>
                </thead>
                <tbody>
                <%
                    try {
                        AlumnoDao dao = new AlumnoDao();
                        List<Alumno> lista = dao.getAll();
                        for (Alumno a : lista) {
                %>
                <tr>
                    <td><%= a.getMatricula() %></td>
                    <td><%= a.getNombre() %></td>
                    <td><%= a.getApellidoPaterno() %></td>
                    <td><%= a.getApellidoMaterno() %></td>
                    <td><%= a.getCorreo() %></td>
                    <td><%= a.getEstado() %></td>
                    <td><%= a.getNombreGrupo() %></td>
                    <td>
                        <a href="registarAlumno.jsp?matricula=<%= a.getMatricula() %>" class="btn btn-act">Actualizar</a>
                    </td>
                    <td>
                        <form method="post" action="eliminarAlumnoServlet">
                            <input type="hidden" name="matricula" value="<%= a.getMatricula() %>">
                            <button type="submit" class="btn btn-del" onclick="return confirmarEliminacion()">Eliminar</button>
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
    </div>
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
        return confirm('¿Estás seguro de que deseas dar de baja a este alumno?');
    }
</script>
</body>
</html>
