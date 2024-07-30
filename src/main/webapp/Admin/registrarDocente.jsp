<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mx.edu.utez.integradiratjuans.dao.DocenteDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Docente" %>
<%
    Docente docente = null;
    String action = "insert";
    String matricula = "";

    String matriculaParam = request.getParameter("matricula");
    if (matriculaParam != null && !matriculaParam.isEmpty()) {
        matricula = matriculaParam;
        action = "update";

        DocenteDao dao = new DocenteDao();
        try {
            docente = dao.getById(matricula);
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
    <title><%= action.equals("update") ? "Actualizar" : "Registrar" %> Registro de Usuario</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
    <h1 class="mb-4"><%= action.equals("update") ? "Actualizar" : "Registrar" %> Usuario</h1>
    <form action="<%=action.equals("update") ? "actualizarDocenteServlet" : "registrarDocenteServlet"%>" method="post">
        <% if (!action.equals("update")) { %>
        <div class="form-group">
            <label for="matricula">Matrícula:</label>
            <input type="text" class="form-control" id="matricula" name="matricula" value="<%= matricula %>" required />
        </div>
        <% } %>

        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="<%= action.equals("update") ? docente.getNombre() : ""%>" required />
        </div>

        <div class="form-group">
            <label for="apellidoPaterno">Apellido Paterno:</label>
            <input type="text" class="form-control" id="apellidoPaterno" name="apellidoPaterno" value="<%= action.equals("update") ? docente.getApellidoPaterno() : ""%>" required />
        </div>

        <div class="form-group">
            <label for="apellidoMaterno">Apellido Materno:</label>
            <input type="text" class="form-control" id="apellidoMaterno" name="apellidoMaterno" value="<%= action.equals("update") ? docente.getApellidoMaterno() : ""%>" required />
        </div>

        <div class="form-group">
            <label for="correo">Correo:</label>
            <input type="email" class="form-control" id="correo" name="correo" value="<%= action.equals("update") ? docente.getCorreo() : ""%>" required />
        </div>

        <div class="form-group">
            <input type="hidden" class="form-control" id="contraseña" name="contraseña" value="<%= action.equals("update") ? docente.getContraseña() : ""%>" required />
        </div>

        <button type="submit" class="btn btn-primary"> <%=action.equals("update") ? "Actualizar" : "Registrar"%></button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
