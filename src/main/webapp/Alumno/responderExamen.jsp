<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Preguntas" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Opcion" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Responder Examen</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card-header {
            font-weight: bold;
        }
        .form-check {
            margin-bottom: 10px;
        }
        .form-group {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">Responder Examen</h1>

    <form action="EnviarRespuestas" method="post">
        <input type="hidden" name="id_examen" value="${idExamen}">
        <%
            List<Preguntas> preguntas = (List<Preguntas>) request.getAttribute("preguntas");
            if (preguntas != null && !preguntas.isEmpty()) {
                for (Preguntas pregunta : preguntas) {
                    List<Opcion> opciones = pregunta.getOpciones();
        %>
        <div class="card mb-3">
            <div class="card-header">
                <%= pregunta.getTexto() %>
            </div>
            <div class="card-body">
                <%
                    String tipoPregunta = pregunta.getTipo();
                    // Depuración
                    System.out.println("Pregunta ID: " + pregunta.getIdPregunta());
                    System.out.println("Tipo de pregunta: " + tipoPregunta);
                    if (opciones != null) {
                        System.out.println("Número de opciones para la pregunta ID " + pregunta.getIdPregunta() + ": " + opciones.size());
                    } else {
                        System.out.println("No hay opciones disponibles.");
                    }

                    if ("opcion_multiple".equals(tipoPregunta)) {
                        for (Opcion opcion : opciones) {
                %>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="pregunta_<%= pregunta.getIdPregunta() %>" value="<%= opcion.getIdOpcion() %>">
                    <label class="form-check-label">
                        <%= opcion.getOpcion() %>
                    </label>
                </div>
                <%
                    }
                } else if ("varias_respuestas".equals(tipoPregunta)) {
                    for (Opcion opcion : opciones) {
                %>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="pregunta_<%= pregunta.getIdPregunta() %>_<%= opcion.getIdOpcion() %>" value="<%= opcion.getIdOpcion() %>">
                    <label class="form-check-label">
                        <%= opcion.getOpcion() %>
                    </label>
                </div>
                <%
                    }
                } else if ("abierta".equals(tipoPregunta)) {
                %>
                <div class="form-group">
                    <label for="pregunta_<%= pregunta.getIdPregunta() %>">
                    </label>
                    <input type="text" class="form-control" id="pregunta_<%= pregunta.getIdPregunta() %>" name="pregunta_<%= pregunta.getIdPregunta() %>" placeholder="Escribe tu respuesta aquí...">
                </div>
                <%
                } else if ("verdaderoFalso".equals(tipoPregunta)) {
                    for (Opcion opcion : opciones) {
                %>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="pregunta_<%= pregunta.getIdPregunta() %>" value="<%= opcion.getIdOpcion() %>">
                    <label class="form-check-label">
                        <%= opcion.getOpcion() %>
                    </label>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <div class="alert alert-warning" role="alert">
            No hay preguntas disponibles para este examen.
        </div>
        <%
            }
        %>
        <button type="submit" class="btn btn-primary">Enviar Respuestas</button>
    </form>
</div>

<!-- Enlace a Bootstrap JS y dependencias (opcional) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
