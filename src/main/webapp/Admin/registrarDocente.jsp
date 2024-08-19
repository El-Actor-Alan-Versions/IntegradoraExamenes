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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables.css">
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
<div class="container ">
    <div class="form text-center d-flex justify-content-center align-items-center vh-100">
        <div class="text-center">
            <form  class="form border-0 text-center" action="<%=action.equals("update") ? "actualizarDocenteServlet" : "registrarDocenteServlet"%>" method="post">
                <img src="../IMG2/login.png" alt="profile icon" class="profile-icon" width="174px">
                <h1><%= action.equals("update") ? "Actualizar" : "Registrar" %> Docente</h1>
                <% if (action.equals("insert")) { %>
                <div class="form-group">
                    <input type="text" placeholder="Matricula" class="form-control" id="matricula" name="matricula" required />
                </div>
                <% } else if (action.equals("update")) { %>
                <div class="form-group">
                    <input type="hidden" class="form-control" id="matriculas" name="matricula" maxlength="150" value="<%= docente.getMatricula()  %>" readonly />
                </div>
                <% } %>
                <div class="form-group mb-3">
                    <input type="text" class="form-control rounded-pill text-center" placeholder="Nombre" id="nombre" maxlength="120" name="nombre" value="<%= action.equals("update") ? docente.getNombre() : ""%>" required />
                </div>

                <div class="form-group mb-3">
                    <input type="text" class="form-control rounded-pill text-center" id="apellidoPaterno" maxlength="120" placeholder="Apellido paterno" name="apellidoPaterno" value="<%= action.equals("update") ? docente.getApellidoPaterno() : ""%>" required />
                </div>

                <div class="form-group mb-3">
                    <input type="text" class="form-control rounded-pill text-center" id="apellidoMaterno"  maxlength="120" placeholder="Apellido materno" name="apellidoMaterno" value="<%= action.equals("update") ? docente.getApellidoMaterno() : ""%>" required />
                </div>

                <div class="form-group mb-3">
                    <input type="hidden" class="form-control" id="correo" name="correo" value="<%= action.equals("update") ? docente.getCorreo() : ""%>" required />
                </div>

                <div class="form-group mb-3">
                    <input type="hidden" class="form-control" id="contraseña" name="contraseña" value="<%= action.equals("update") ? docente.getContraseña() : ""%>" required />
                </div>
                <div class="form-floating mt-3">
                    <button type="submit"  class="btn rounded-pill"> <%=action.equals("update") ? "Actualizar" : "Registrar"%></button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
