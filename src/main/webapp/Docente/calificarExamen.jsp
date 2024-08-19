<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Preguntas" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Opcion" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Respuesta" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.ExamenDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calificar Examen</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');

        body {
            font-family: 'PT Sans';
            justify-content: center;
            align-items: center;
            background: #EEEEEE;
            width: 100%;
            height: 100%;
        }
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
        .card{
            margin: auto;
            box-shadow: 0 3px 5px rgba(0, 0, 0, 0.2);
            border-radius: 6px;
            width: 100%;
            max-width: 900px;
            margin-bottom: 20px;
            max-width: 482px;
        }
        #card-preguntas{
            background-color: transparent;
            border-color: transparent;
            box-shadow: none;
        }
        .container{
            width: 100%;
            max-width: 1000px;
        }
        .btn-enviar{
            background: #97E3D2;
            border-radius: 20px;
            font-weight: 500;
            margin-left: 40%;
        }
        #card-title{
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">

    <div class="mb-3 card">
        <div class="header-card"></div>
             <div class="col-md-8 card-body">
            <div class="card-text">
    <!-- Nombrear el examen -->
    <%
        ExamenDao examenDao = new ExamenDao();
        Integer idExamen = (Integer) session.getAttribute("idExamen");
        String nombreExamen = examenDao.obtenerNombreExamenPorId(idExamen);
    %>
    <h3>Calificar Examen <%= nombreExamen %></h3>

    <%
        List<Preguntas> preguntas = (List<Preguntas>) request.getAttribute("preguntas");
        Map<Integer, List<Opcion>> opcionesPorPregunta = (Map<Integer, List<Opcion>>) request.getAttribute("opcionesPorPregunta");
        Map<Integer, Respuesta> respuestasAlumno = (Map<Integer, Respuesta>) request.getAttribute("respuestasAlumno");
    %>
             </div>
         </div>
   </div>
    <% if (preguntas != null && !preguntas.isEmpty()) { %>
    <form action="guardarCalificacion" method="post">
        <% for (Preguntas pregunta : preguntas) { %>
        <div class="mb-3 card">
            <div class="header-card"></div>
            <div class="col-md-8 card-body">
                <div class="card-text">
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
        </div>
        <% } %>
        <button type="submit" class="btn btn-primary">Guardar Calificaciones</button>
    </form>
    <% } else { %>1
    <p>No se encontraron preguntas para este examen.</p>
    <% } %>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>