<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mx.edu.utez.integradiratjuans.dao.AdministradorDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Administrador" %>
<%@ page import="java.util.List" %>

<%
    Administrador administrador = null;
    String action = "insert";
    String matricula = "";

    String matriculaParam = request.getParameter("matricula");
    if (matriculaParam != null && !matriculaParam.isEmpty()) {
        matricula = matriculaParam;
        action = "update";

        AdministradorDao dao = new AdministradorDao();
        try {
            administrador = dao.getById(matricula);
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
    <title><%= action.equals("update") ? "Actualizar" : "Registrar" %> </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registroUsuarios.css">
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
    <div class="d-flex justify-content-center align-items-center ">
        <div class="text-center">
            <form class="border-0 text-center" action="<%= action.equals("update") ? "ActualizarAdministradorServlet" : "RegistrarAdministradorServlet"%>" method="post">
                <img src="../IMG2/login.png" alt="profile icon" class="profile-icon" width="174px">
                <h1><%= action.equals("update") ? "Actualizar" : "Registrar" %> Administrador</h1>
                <% if (action.equals("insert")) { %>
                <div class="form-group mb-3">
                    <input type="text" class="form-control" id="matricula" maxlength="120" name="matricula" placeholder="Matricula" value="<%= action.equals("update") ? administrador.getMatricula() : "" %>" required />
                </div>
                <% } %>
                <div class="form-group mb-3">
                    <input type="text" class="form-control rounded-pill text-center"  maxlength="120" placeholder="Nombre" id="nombre" name="nombre" value="<%= action.equals("update") ? administrador.getNombre() : "" %>" required />
                </div>

                <div class="form-group mb-3">
                    <input type="text" class="form-control rounded-pill text-center" id="apellidoPaterno"  maxlength="120" placeholder="Apellido paterno" name="apellidoPaterno" value="<%= action.equals("update") ? administrador.getApellidoPaterno() : "" %>" required />
                </div>

                <div class="form-group mb-3">
                    <input type="text" class="form-control rounded-pill text-center" id="apellidoMaterno" maxlength="120" placeholder="Apellido materno" name="apellidoMaterno" value="<%= action.equals("update") ? administrador.getApellidoMaterno() : "" %>" required />
                </div>

                <div class="form-group mb-3">
                    <input type="hidden" class="form-control rounded-pill text-center" id="correo"  placeholder="Correo" name="correo" value="<%= action.equals("update") ? administrador.getCorreo() : "" %>" required />
                </div>

                <% if (action.equals("insert")) { %>
                <div class="form-group mb-3">
                    <input type="hidden" class="form-control rounded-pill text-center" id="contraseña" placeholder="Contraseña" name="contraseña" required />
                </div>
                <% } %>

                <div class="form-floating mt-3">
                    <button type="submit" class="btn rounded-pill"> <%= action.equals("update") ? "Actualizar" : "Registrar" %></button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Incluir el JS de Bootstrap y dependencias -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
