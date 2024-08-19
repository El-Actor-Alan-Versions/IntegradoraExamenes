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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables.css">
    <title><%= action.equals("update") ? "Actualizar" : "Registrar" %> Alumno</title>
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
    <div class="d-flex justify-content-center align-items-center >
        <div class="text-center">
            <form class="border-0 text-center" action="<%= action.equals("update") ? "ActualizarAlumnoServlet" : "RegistrarAlumnoServlet"%>" method="post">
                <img src="../IMG2/login.png" alt="profile icon" class="profile-icon" width="174px">
                <h1><%= action.equals("update") ? "Actualizar" : "Registrar" %> Alumno</h1>
                <% if (action.equals("insert")) { %>
                <div class="form-group">
                    <input type="text" placeholder="Matricula" class="form-control" maxlength="120" id="matriculaInsert" name="matriculaInsert" required />
                </div>
                <% } else if (action.equals("update")) { %>
                <div class="form-group">
                    <input type="hidden" class="form-control" id="matricula" name="matricula"  value="<%= alumno.getMatricula()  %>" readonly />
                </div>
                <% } %>
                <div class="form-group mb-3">
                    <input type="text" class="form-control rounded-pill text-center" placeholder="Nombre"  maxlength="120" id="nombre" name="nombre" value="<%= action.equals("update") ? alumno.getNombre() : "" %>" required />
                </div>

                <div class="form-group mb-3">
                    <input type="text" class="form-control rounded-pill text-center" id="apellidoPaterno" maxlength="120" placeholder="Apellido paterno" name="apellidoPaterno" value="<%= action.equals("update") ? alumno.getApellidoPaterno() : "" %>" required />
                </div>

                <div class="form-group mb-3">
                    <input type="text" class="form-control rounded-pill text-center" id="apellidoMaterno" maxlength="120" placeholder="Apellido materno" name="apellidoMaterno" value="<%= action.equals("update") ? alumno.getApellidoMaterno() : "" %>" required />
                </div>

                <div class="form-group mb-3">
                    <input type="hidden" class="form-control" id="correo" name="correo" value="<%= action.equals("update") ? alumno.getCorreo() : "" %>" required />
                </div>

                <% if (action.equals("update")) { %>
                <div class="form-group mb-3">
                    <input type="hidden" class="form-control" id="contraseña" name="contraseña" value="<%= action.equals("update") ? alumno.getContraseña() : ""%>" required />
                </div>
                <% } %>

                <div class="form-group mb-3">
                    <select id="gruposSelect" class="form-control" name="idGrupo" required>
                        <option value="">Seleccione...</option>
                        <%
                            GrupoDao grupoDao = new GrupoDao();
                            List<Grupo> grupos = grupoDao.getAll();
                            for (Grupo grupo : grupos) {
                        %>
                        <option  value="<%= grupo.getIdGrupo() %>" <%= action.equals("update") && grupo.getIdGrupo() == alumno.getIdGrupo() ? "selected" : "" %>><%= grupo.getGradoGrupo() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="form-floating mt-3">
                    <button type="submit" class="btn rounded-pill"> <%= action.equals("update") ? "Actualizar" : "Registrar" %></button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Incluir el JS de Bootstrap y dependencias -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
