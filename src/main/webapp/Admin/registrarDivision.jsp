<%@ page import="mx.edu.utez.integradiratjuans.dao.DivisionDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Division" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Division division = null;
    String action = "insert";
    int idDivision = 0;

    // Si se pasa un idDivision en la URL, cargamos los datos para actualización
    String idDivisionParam = request.getParameter("idDivision");
    if (idDivisionParam != null && !idDivisionParam.isEmpty()) {
        idDivision = Integer.parseInt(idDivisionParam);
        action = "update";

        DivisionDao dao = new DivisionDao();
        try {
            division = dao.getById(idDivision);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= action.equals("update") ? "Actualizar" : "Registrar" %> División</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<div class="container mt-4">
    <h2><%= action.equals("update") ? "Actualizar" : "Registrar" %> División</h2>
    <form action="<%= action.equals("update") ? "actualizarDivisionServlet" : "registrarDivisionServlet" %>" method="post">
        <% if (action.equals("update")) { %>
        <input type="hidden" name="idDivision" value="<%= division.getIdDivision() %>">
        <% } %>
        <div class="mb-3">
            <label for="nombreDivision" class="form-label">Nombre de la División</label>
            <input type="text" class="form-control" id="nombreDivision" name="nombreDivision" value="<%= action.equals("update") ? division.getNombreDivision() : "" %>" required>
        </div>
        <button type="submit" class="btn btn-primary"><%= action.equals("update") ? "Actualizar" : "Registrar" %></button>
    </form>
</div>
<script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath}/JS/bootstrap.js"></script>
</body>
</html>
