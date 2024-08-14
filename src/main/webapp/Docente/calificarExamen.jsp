<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Preguntas" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Opcion" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Respuesta" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <div class="mb-3 row">
            <div class="col-md-8">
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
                <strong>Respuestas Correctas:</strong>
                <ul>
                    <%
                        // Filtrar y mostrar todas las respuestas correctas
                        List<Opcion> respuestasCorrectas = opciones.stream()
                                .filter(Opcion::isCorrecta)
                                .collect(Collectors.toList());

                        for (Opcion correcta : respuestasCorrectas) {
                    %>
                    <li><%= correcta.getOpcion() %></li>
                    <% } %>
                </ul>
                <strong>Respuesta del Alumno:</strong>
                <%
                    Respuesta respuestaAlumno = respuestasAlumno.get(pregunta.getIdPregunta());
                    boolean esCorrecta = false;

                    if (respuestaAlumno != null) {
                        List<String> respuestasAlumnoLista = List.of(respuestaAlumno.getRespuesta().split(","));
                        esCorrecta = respuestasCorrectas.stream()
                                .map(Opcion::getOpcion)
                                .collect(Collectors.toSet())
                                .equals(respuestasAlumnoLista.stream().map(String::trim).collect(Collectors.toSet()));
                    }
                %>
                <%= respuestaAlumno != null ? respuestaAlumno.getRespuesta() : "N/A" %>
            </div>

            <!-- Campo para que el profesor indique si la respuesta es correcta o no -->
            <div class="col-md-4 d-flex align-items-center">
                <div class="form-group w-100">
                    <label for="calificacion_<%= pregunta.getIdPregunta() %>">Calificación:</label>
                    <input type="hidden" name="pregunta_<%= pregunta.getIdPregunta() %>" value="<%= respuestaAlumno != null ? respuestaAlumno.getIdRespuesta() : "" %>">
                    <input type="number" id="calificacion_<%= pregunta.getIdPregunta() %>" name="calificacion_<%= pregunta.getIdPregunta() %>"
                           value="<%= esCorrecta ? 1 : 0 %>"
                           min="0" max="1" class="form-control">
                </div>
            </div>
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