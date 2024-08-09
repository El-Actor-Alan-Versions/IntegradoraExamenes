<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Preguntas" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Opcion" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Respuesta" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calificar Examen</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Calificar Examen</h1>

    <%
        List<Preguntas> preguntas = (List<Preguntas>) request.getAttribute("preguntas");
        Map<Integer, List<Opcion>> opcionesPorPregunta = (Map<Integer, List<Opcion>>) request.getAttribute("opcionesPorPregunta");
        Map<Integer, Respuesta> respuestasAlumno = (Map<Integer, Respuesta>) request.getAttribute("respuestasAlumno");
    %>

    <% if (preguntas != null && !preguntas.isEmpty()) { %>
    <form action="guardarCalificacion" method="post">
        <% for (Preguntas pregunta : preguntas) { %>
        <div class="mb-3">
            <strong>Pregunta:</strong> <%= pregunta.getTexto() %><br>
            <strong>Opciones:</strong>
            <ul>
                <% List<Opcion> opciones = opcionesPorPregunta.get(pregunta.getIdPregunta()); %>
                <% if (opciones != null) { %>
                <% for (Opcion opcion : opciones) { %>
                <li><%= opcion.getOpcion() %></li>
                <% } %>
                <% } %>
            </ul>
            <strong>Respuesta Correcta:</strong>
            <%
                Opcion correcta = opciones.stream()
                        .filter(o -> o.isCorrecta()) // Ajusta según cómo determines la respuesta correcta
                        .findFirst()
                        .orElse(null);
            %>
            <%= correcta != null ? correcta.getOpcion() : "N/A" %><br>
            <strong>Respuesta del Alumno:</strong>
            <%
                Respuesta respuestaAlumno = respuestasAlumno.get(pregunta.getIdPregunta());
            %>
            <%= respuestaAlumno != null ? respuestaAlumno.getRespuesta() : "N/A" %>
        </div>
        <% } %>
        <button type="submit" class="btn btn-primary">Guardar Calificaciones</button>
    </form>
    <% } else { %>
    <p>No se encontraron preguntas para este examen.</p>
    <% } %>
</div>
</body>
</html>
