<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Pregunta" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Opcion" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vista Previa</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="my-4">Vista Previa de Preguntas</h2>
    <%
        List<Pregunta> preguntas = (List<Pregunta>) request.getAttribute("preguntas");
        for (Pregunta pregunta : preguntas) {
    %>
    <div class="card my-3">
        <div class="card-body">
            <h3 class="card-title"><%= pregunta.getTexto() %></h3>
            <p class="card-text"><strong>Tipo de Pregunta:</strong> <%= pregunta.getTipo() %></p>
            <%
                if (pregunta.getTipo().equals("multiple_choice") || pregunta.getTipo().equals("multiple_answers")) {
                    for (Opcion opcion : pregunta.getOpciones()) {
            %>
            <p class="card-text"><%= opcion.getTexto() %> <% if (opcion.isCorrecta()) { %> <span class="badge badge-success">Correcta</span> <% } %></p>
            <%
                }
            } else if (pregunta.getTipo().equals("open_ended")) {
            %>
            <p class="card-text"><strong>Respuesta:</strong> <%= pregunta.getRespuesta() %></p>
            <%
                }
            %>
        </div>
    </div>
    <%
        }
    %>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
