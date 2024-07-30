<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mx.edu.utez.integradiratjuans.dao.AlumnoDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Alumno" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.GrupoDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Grupo" %>
<%@ page import="java.util.List" %>

<%
    Alumno alumno = null;
    String action = "insert";
    String matricula = "";

    String matriculaParam = request.getParameter("matricula");
    if (matriculaParam != null && !matriculaParam.isEmpty()) {
        matricula = matriculaParam;
        action = "update";

        AlumnoDao dao = new AlumnoDao();
        try {
            alumno = dao.getById(matricula);
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <title><%= action.equals("update") ? "Actualizar" : "Registrar" %> Alumno</title>
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
    <h1 class="mb-4"><%= action.equals("update") ? "Actualizar" : "Registrar" %> Alumno</h1>
    <form action="<%=action.equals("update") ? "ActualizarAlumnoServlet" : "RegistrarAlumnoServlet"%>" method="post">
        <% if (action.equals("update")) { %>
        <input type="hidden" name="matricula" value="<%= matricula %>">
        <% } %>

        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="<%= action.equals("update") ? alumno.getNombre() : ""%>" required />
        </div>

        <div class="form-group">
            <label for="apellidoPaterno">Apellido Paterno:</label>
            <input type="text" class="form-control" id="apellidoPaterno" name="apellidoPaterno" value="<%= action.equals("update") ? alumno.getApellidoPaterno() : ""%>" required />
        </div>

        <div class="form-group">
            <label for="apellidoMaterno">Apellido Materno:</label>
            <input type="text" class="form-control" id="apellidoMaterno" name="apellidoMaterno" value="<%= action.equals("update") ? alumno.getApellidoMaterno() : ""%>" required />
        </div>

        <% if (!action.equals("update")) { %>
        <div class="form-group">
            <label for="contraseña">Contraseña:</label>
            <input type="password" class="form-control" id="contraseña" name="contraseña" required />
        </div>
        <% } %>

        <div class="form-group">
            <label for="gruposSelect">Grupo:</label>
            <select id="gruposSelect" class="form-control" name="idGrupo">
                <option value="">Seleccione...</option>
                <%
                    GrupoDao grupoDao = new GrupoDao();
                    List<Grupo> grupos = grupoDao.getAll();
                    for (Grupo grupo : grupos) {
                %>
                <option value="<%= grupo.getIdGrupo() %>" <%= action.equals("update") && grupo.getIdGrupo() == alumno.getIdGrupo() ? "selected" : "" %>><%= grupo.getGradoGrupo() %></option>
                <%
                    }
                %>
            </select>
        </div>

        <button type="submit" class="mt-4 btn btn-primary"><%= action.equals("update") ? "Actualizar" : "Registrar" %></button>
    </form>
</div>

<!-- Incluir el JS de Bootstrap y dependencias -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
