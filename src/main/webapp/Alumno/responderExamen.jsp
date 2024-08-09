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

        @import url('https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');

        body {
            font-family: 'PT Sans';
            color: rgb(17, 16, 16);
            justify-content: center;
            align-items: center;
            background-color: #EEEEEE;
            width: 100%;
            margin: 0;
            padding: 0;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);

        }
        .card-header {
            background: #97e3d2;
            justify-content: center;
            border-top-left-radius: 22px;
            border-top-right-radius: 22px;  
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
    <div class="card-header">
        <h1 class="mb-4">Responder Examen</h1>
    </div>
    <div class="card-body">
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
                        if ("opcion_multiple".equals(tipoPregunta)) {
                            for (Opcion opcion : opciones) {
                    %>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="pregunta_<%= pregunta.getIdPregunta() %>" value="<%= opcion.getOpcion() %>">
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
                        <input class="form-check-input" type="checkbox" name="pregunta_<%= pregunta.getIdPregunta() %>_<%= opcion.getOpcion() %>" value="<%= opcion.getOpcion() %>">
                        <label class="form-check-label">
                            <%= opcion.getOpcion() %>
                        </label>
                    </div>
                    <%
                        }
                    } else if ("abierta".equals(tipoPregunta)) {
                    %>
                    <div class="form-group">
                        <input type="text" class="form-control" id="pregunta_<%= pregunta.getIdPregunta() %>" name="pregunta_<%= pregunta.getIdPregunta() %>" placeholder="Escribe tu respuesta aquí...">
                    </div>
                    <%
                    } else if ("verdadero_falso".equals(tipoPregunta)) {
                    %>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="pregunta_<%= pregunta.getIdPregunta() %>" value="Verdadero" id="verdadero_<%= pregunta.getIdPregunta() %>">
                        <label class="form-check-label" for="verdadero_<%= pregunta.getIdPregunta() %>">
                            Verdadero
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="pregunta_<%= pregunta.getIdPregunta() %>" value="Falso" id="falso_<%= pregunta.getIdPregunta() %>">
                        <label class="form-check-label" for="falso_<%= pregunta.getIdPregunta() %>">
                            Falso
                        </label>
                    </div>
                    <%
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
</div>

<!-- Enlace a Bootstrap JS y dependencias (opcional) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
