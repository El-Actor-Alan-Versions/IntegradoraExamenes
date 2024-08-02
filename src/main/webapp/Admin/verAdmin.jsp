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
            <h2>Gestión de Administradores</h2>
            <button type="button" class="btn btn-new" onclick="location.href='registrarAdmin.jsp'">Nuevo administrador</button>
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
                    <td class="btns">
                        <a href="registrarAdmin.jsp?matricula=<%= a.getMatricula() %>" class="btn btn-act">Actualizar</a>
                    </td>
                    <td class="btns">
                        <form method="post" action="eliminarAdministradorServlet">
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
        return confirm('¿Estás seguro de que deseas dar de baja a este administrador?');
    }
</script>
</body>
</html>
