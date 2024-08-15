<!-- <%@ page import="mx.edu.utez.integradiratjuans.model.Preguntas" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Opcion" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vista Previa de Preguntas</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
            font-family: 'PT Sans';
            justify-content: center;
            align-items: center;
            background: #EEEEEE;
            width: 100%;
            height: 100%;
            font-size: 16px;
        }

        .container{
            margin-top: 20px;
        }

        /* Titulo */
        .header-card{
            background-color: #97E3D2;
            display: flex;
            width: 100%;
            height: 12px;
            border-top-left-radius: 6px;
            border-top-right-radius: 6px;
        }

        .title-prg{
            font-size: 32px;
            font-weight: 500;
        }

        /* Todas las cards */
        .card{
            margin: auto;
            box-shadow: 0 3px 5px rgba(0, 0, 0, 0.2);
            border-radius: 6px;
            width: 100%;
            max-width: 800px;
            margin-bottom: 20px;
        }

        footer{
            height: 20%;
        }

        . btn-guardar{
            border-radius: 20px;
            font-weight: 530;
            width: 150px;
            padding: 0.5em;
            box-sizing: border-box;
        }


        .btn-edi     {
            border-radius: 20px;
            font-weight: 530;
            width: 150px;
            padding: 0.5em;
            box-sizing: border-box;
        }

        .btn-guardar {
            background-color: #85C5B7;
        }

        .btn-edi {
            background-color: #BAF1B4;
        }

        @media (max-width: 768px) {
            .btn-guardar{
                margin-left: 20px;
                width: 150px;
            }
            .btn-edi {
                width: 150px;
            }

        }
    </style>
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
<div class="container">
    <div class="card">
        <div class="header-card"></div>
        <div class="card-body">
            <div class="card-text">
                <!-- Ver como mostrar el nombre del examen -->
                <p class="title-prg">Vista previa examen </p>
            </div>
        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <div class="card-text">
                <form action="InsertarPreguntaServlet" method="post">
                    <%
                        List<Preguntas> preguntas = (List<Preguntas>) request.getAttribute("preguntas");
                        if (preguntas != null && !preguntas.isEmpty()) {
                            for (int i = 0; i < preguntas.size(); i++) {
                                Preguntas pregunta = preguntas.get(i);
                    %>
                    <div class="border p-3 my-3">
                        <p class="input"><strong><%= (i + 1) %>.</strong> <%= pregunta.getTexto() %></p>
                        <p class="label" type="hidden"><strong>Tipo de Pregunta:</strong> <%= pregunta.getTipo() %></p>
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
                        } else if ("verdadero_falso".equals(pregunta.getTipo())) {
                            // Asumimos que solo hay dos opciones: Verdadero y Falso
                            List<Opcion> opciones = pregunta.getOpciones();
                            if (opciones != null && !opciones.isEmpty()) {
                                Opcion opcionVerdadero = opciones.get(0); // Primera opción es Verdadero
                                Opcion opcionFalso = opciones.get(1); // Segunda opción es Falso
                        %>
                        <p>Opción Verdadero: <%= opcionVerdadero.isCorrecta() ? "<strong>Correcta</strong>" : "" %></p>
                        <p>Opción Falso: <%= opcionFalso.isCorrecta() ? "<strong>Correcta</strong>" : "" %></p>
                        <%
                        } else {
                        %>
                        <p>No hay opciones disponibles para esta pregunta.</p>
                        <%
                                }
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
                            }
                        } else if ("abierta".equals(pregunta.getTipo())) {
                        %>
                        <input type="hidden" name="questions[<%= i %>].openEndedAnswer" value="<%= pregunta.getRespuesta() %>">
                        <%
                        } else if ("verdadero_falso".equals(pregunta.getTipo())) {
                            // Campos ocultos para opciones de verdadero/falso
                            List<Opcion> opciones = pregunta.getOpciones();
                            if (opciones != null && !opciones.isEmpty()) {
                                Opcion opcionVerdadero = opciones.get(0); // Primera opción es Verdadero
                                Opcion opcionFalso = opciones.get(1); // Segunda opción es Falso
                        %>
                        <input type="hidden" name="questions[<%= i %>].option1" value="Verdadero">
                        <input type="hidden" name="questions[<%= i %>].correctOption1" value="<%= opcionVerdadero.isCorrecta() ? "true" : "false" %>">
                        <input type="hidden" name="questions[<%= i %>].option2" value="Falso">
                        <input type="hidden" name="questions[<%= i %>].correctOption2" value="<%= opcionFalso.isCorrecta() ? "true" : "false" %>">
                        <%
                                }
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
                    <button type="submit" class="btn btn-guardar">Guardar Preguntas</button>
                    <a href="crearPreguntas.jsp" class="btn btn-edi">Editar</a>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap JS y dependencias -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
