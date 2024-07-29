<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mx.edu.utez.integradiratjuans.dao.AlumnoDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Alumno" %>

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
    <title>Docente</title>
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
    <form action="<%=action.equals("update") ? "actualizarAlumnoServlet" : "registrarAlumnoServlet"%>" method="post">
        <% if (action.equals("update")) { %>
        <input type="hidden" name="matricula" value="<%= alumno.getMatricula() %>">
        <% } %>

        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="<%= action.equals("update") ? alumno.getNombre() : ""%>" required />
        </div>

        <div class="form-group">
            <label for="apellidoPaterno">Apellido Paterno:</label>
            <input type="text" class="form-control" id="apellidoPaterno" name="apellidoPaterno" value="<%= action.equals("update") ? alumno.getApellidoPaterno() : ""%>"  required />
        </div>

        <div class="form-group">
            <label for="apellidoMaterno">Apellido Materno:</label>
            <input type="text" class="form-control" id="apellidoMaterno" name="apellidoMaterno" value="<%= action.equals("update") ? alumno.getApellidoMaterno() : ""%>" required />
        </div>

        <div class="form-group">
            <label for="correo">Correo:</label>
            <input type="email" class="form-control" id="correo" name="correo" value="<%= action.equals("update") ? alumno.getCorreo() : ""%>" required />
        </div>

        <div class="form-group">
            <label for="contraseña">Contraseña:</label>
            <input type="password" class="form-control" id="contraseña" name="contraseña" value="<%= action.equals("update") ? alumno.getContraseña() : ""%>" required />
        </div>

        <div class="form-group">
            <label for="gruposSelect" >Grupo:</label>
            <select id="gruposSelect" class="form-control" name="idGrupo">
                <option value="<%= action.equals("update") ? alumno.getNombreGrupo() : ""%>">Seleccione...</option>
            </select>
        </div>

        <button type="submit" class="mt-4 btn btn-primary"><%=action.equals("update") ? "Actualizar" : "Registrar"%></button>
    </form>
</div>

<!-- Incluir el JS de Bootstrap y dependencias -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        fetch('${pageContext.request.contextPath}/GetGruposServlet')
            .then(response => response.json())
            .then(data => {
                const selectElement = document.getElementById('gruposSelect');
                data.forEach(grupo => {
                    const option = document.createElement('option');
                    option.value = grupo.idGrupo;
                    option.text = grupo.gradoGrupo;
                    selectElement.appendChild(option);
                });
            })
            .catch(error => console.error('Error:', error));
    });
</script>
</body>
</html>
