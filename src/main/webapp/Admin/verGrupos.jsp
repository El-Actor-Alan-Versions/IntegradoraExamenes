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
            <img src="../img/calificaciones.png" width="68">
            <h2>Gestión de grupos</h2>
            <button type="button" class="btn btn-new" onclick="location.href='registrarGrupo.jsp'">Nuevo grupo</button>
            <button type="button" class="btn btn-conf" onclick="location.href='indexAdmin.jsp'">Volver</button>
        </div>
        <div class="card-body">
            <table id="example" class="table table-custom">
                <thead>
                <tr>
                    <th>ID Grupo</th>
                    <th>Grado Grupo</th>
                    <th>Carrera</th>
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
                    <td><%= g.getNombreCarrera() %></td>
                    <td>
                        <a href="registrarGrupo.jsp?idGrupo=<%= g.getIdGrupo() %>" class="btn btn-act">Modificar</a>
                    </td>
                    <td>
                        <form method="post" action="eliminarGrupoServlet">
                            <input type="hidden" name="idGrupo" value="<%= g.getIdGrupo() %>">
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
