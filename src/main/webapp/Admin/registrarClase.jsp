<%@ page import="mx.edu.utez.integradiratjuans.dao.GrupoDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Grupo" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.MateriaDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Materia" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.DocenteDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Docente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Clase</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-custom">
    <div class="profile-button">
        <img src="${pageContext.request.contextPath}/img/Logo-utez%20.png" id="logo" alt="Logo">
    </div>
    <div class="d-flex flex-grow-1 justify-content">
        <p class="navbar-text">PLATAFORMA DE EXÁMENES</p>
    </div>
    <img src="${pageContext.request.contextPath}/img/miPerfil.png" alt="perfil">
</nav>

<div class="container mt-4">
    <h2>Registrar Nueva Clase</h2>
    <form action="registrarClaseServlet" method="post">
        <div class="form-group">
            <label for="idGrupo">Grupo:</label>
            <select id="gruposSelect" class="form-control" name="idGrupo">
                <option value="">Seleccione...</option>
                <%
                    GrupoDao grupoDao = new GrupoDao();
                    List<Grupo> grupos = grupoDao.getAll();
                    for (Grupo grupo : grupos) {
                %>
                <option value="<%= grupo.getIdGrupo() %>"><%= grupo.getGradoGrupo() %></option>
                <%
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <label for="idMateria">Materia:</label>
            <select id="materiasSelect" class="form-control" name="idMateria">
                <option value="">Seleccione...</option>
                <%
                    MateriaDao materiaDao = new MateriaDao();
                    List<Materia> materias = materiaDao.getAll();
                    for (Materia materia : materias) {
                %>
                <option value="<%= materia.getIdMateria() %>"><%= materia.getNombreMateria() %></option>
                <%
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <label for="idDocente">Docente:</label>
            <select id="docentesSelect" class="form-control" name="idDocente">
                <option value="">Seleccione...</option>
                <%
                    DocenteDao docenteDao = new DocenteDao();
                    List<Docente> docentes = docenteDao.getAll();
                    for (Docente docente : docentes) {
                %>
                <option value="<%= docente.getMatricula() %>"><%= docente.getNombre() %> <%= docente.getApellidoPaterno() %> <%= docente.getApellidoMaterno() %></option>
                <%
                    }
                %>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Registrar Clase</button>
    </form>
</div>

<script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath}/JS/bootstrap.js"></script>
</body>
</html>
