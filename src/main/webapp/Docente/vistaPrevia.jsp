<%@ page import="mx.edu.utez.integradiratjuans.model.Preguntas" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Opcion" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vista Previa de Preguntas</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Vista Previa de Preguntas</h2>
    <form action="InsertarPreguntaServlet" method="post">
        <%
            List<Preguntas> preguntas = (List<Preguntas>) request.getAttribute("preguntas");
            if (preguntas != null && !preguntas.isEmpty()) {
                for (int i = 0; i < preguntas.size(); i++) {
                    Preguntas pregunta = preguntas.get(i);
        %>
        <div class="border p-3 my-3">
            <h3>Pregunta <%= (i + 1) %></h3>
            <p><strong>Pregunta:</strong> <%= pregunta.getTexto() %></p>
            <p><strong>Tipo de Pregunta:</strong> <%= pregunta.getTipo() %></p>
            <%
                if ("opcion_multiple".equals(pregunta.getTipo()) || "varias_respuestas".equals(pregunta.getTipo())) {
                    List<Opcion> opciones = pregunta.getOpciones();
                    if (opciones != null && !opciones.isEmpty()) {
                        for (int j = 0; j < opciones.size(); j++) {
                            Opcion opcion = opciones.get(j);
            %>
            <p>Opción <%= (j + 1) %>: <%= opcion.getOpcion() %> <% if (opcion.isCorrecta()) { %> <strong>(Correcta)</strong> <% } %></p>
            <%
                }
            } else {
            %>
            <p>No hay opciones disponibles para esta pregunta.</p>
            <%
                }
            } else if ("abierta".equals(pregunta.getTipo())) {
            %>
            <p><strong>Respuesta:</strong> <%= pregunta.getRespuesta() %></p>
            <%
                }
            %>
            <!-- Campos ocultos para enviar los datos al servlet de inserción -->
            <input type="hidden" name="questions[<%= i %>].pregunta" value="<%= pregunta.getTexto() %>">
            <input type="hidden" name="questions[<%= i %>].questionType" value="<%= pregunta.getTipo() %>">
            <%
                if ("opcion_multiple".equals(pregunta.getTipo()) || "varias_respuestas".equals(pregunta.getTipo())) {
                    List<Opcion> opciones = pregunta.getOpciones();
                    if (opciones != null && !opciones.isEmpty()) {
                        for (int j = 0; j < opciones.size(); j++) {
                            Opcion opcion = opciones.get(j);
            %>
            <input type="hidden" name="questions[<%= i %>].option<%= (j + 1) %>" value="<%= opcion.getOpcion() %>">
            <input type="hidden" name="questions[<%= i %>].correctOption<%= (j + 1) %>" value="<%= opcion.isCorrecta() ? "true" : "false" %>">
            <%
                }
            } else {
            %>
            <input type="hidden" name="questions[<%= i %>].optionsAvailable" value="false">
            <%
                }
            } else if ("abierta".equals(pregunta.getTipo())) {
            %>
            <input type="hidden" name="questions[<%= i %>].openEndedAnswer" value="<%= pregunta.getRespuesta() %>">
            <%
                }
            %>
        </div>
        <%
            }
        } else {
        %>
        <p>No hay preguntas para mostrar en la vista previa.</p>
        <%
            }
        %>
        <button type="submit" class="btn btn-success">Guardar Preguntas</button>
        <a href="crearPreguntas.jsp" class="btn btn-secondary">Editar</a>
    </form>
</div>
<!-- Bootstrap JS y dependencias -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
