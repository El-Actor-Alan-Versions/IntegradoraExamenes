<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.DivisionDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Division" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <title>Registrar carrera</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/usuarios.css">

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
    <h2>Registrar Nueva Carrera</h2>
    <form action="registrarCarreraServlet" method="post">
        <div class="form-group">
            <label for="nombreCarrera">Nombre de la Carrera:</label>
            <input type="text" class="form-control" id="nombreCarrera" name="nombreCarrera" required>
        </div>

        <div class="form-group">
            <label for="idDivision">División:</label>
            <select id="divisionesSelect" class="form-control" name="idDivision" required>
                <option value="">Seleccione...</option>
                <%
                    DivisionDao divisionDao = new DivisionDao();
                    List<Division> divisiones = divisionDao.getAll();
                    for (Division division : divisiones) {
                %>
                <option value="<%= division.getIdDivision() %>"><%= division.getNombreDivision() %></option>
                <%
                    }
                %>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Registrar Carrera</button>
    </form>
</div>

<script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath}/JS/bootstrap.js"></script>
</body>
</html>
