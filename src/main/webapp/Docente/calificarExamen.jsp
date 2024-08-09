<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Preguntas, mx.edu.utez.integradiratjuans.model.Opcion, mx.edu.utez.integradiratjuans.model.Respuesta" %>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calificar Examen</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">Calificar Examen</h1>

    <form action="CalificarExamenServlet" method="post">
        <input type="hidden" name="id_examen" value="<%= request.getAttribute("id_examen") %>">

        <%
            List<Preguntas> preguntas = (List<Preguntas>) request.getAttribute("preguntas");
            Map<Integer, List<Opcion>> opcionesPorPregunta = (Map<Integer, List<Opcion>>) request.getAttribute("opcionesPorPregunta");
            Map<Integer, Respuesta> respuestasMap = (Map<Integer, Respuesta>) request.getAttribute("respuestasMap");

            // Mensajes de depuración
            if (preguntas == null) {
                System.out.println("No se recibieron preguntas.");
            } else {
                System.out.println("Preguntas recibidas: " + preguntas.size());
            }

            if (opcionesPorPregunta == null) {
                System.out.println("No se recibieron opciones por pregunta.");
            } else {
                System.out.println("Opciones por pregunta: " + opcionesPorPregunta.size());
            }

            if (respuestasMap == null) {
                System.out.println("No se recibieron respuestas.");
            } else {
                System.out.println("Respuestas recibidas: " + respuestasMap.size());
            }

            // Verifica si los datos son nulos antes de renderizar
            if (preguntas != null && opcionesPorPregunta != null && respuestasMap != null) {
        %>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Pregunta</th>
                <th>Opciones</th>
                <th>Respuesta del Alumno</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Preguntas pregunta : preguntas) {
                    List<Opcion> opciones = opcionesPorPregunta.get(pregunta.getIdPregunta());
                    Respuesta respuesta = respuestasMap.get(pregunta.getIdPregunta());
            %>
            <tr>
                <td><%= pregunta.getTexto() %></td>
                <td>
                    <%
                        if (opciones != null) {
                            for (Opcion opcion : opciones) {
                                boolean isChecked = respuesta != null && respuesta.getRespuesta() != null &&
                                        respuesta.getRespuesta().equals(String.valueOf(opcion.getIdOpcion()));
                    %>
                    <div class="form-check">
                        <input type="radio" class="form-check-input" name="id_pregunta_<%= pregunta.getIdPregunta() %>" value="<%= opcion.getIdOpcion() %>"
                            <%= isChecked ? "checked" : "" %> disabled>
                        <label class="form-check-label"><%= opcion.getOpcion() %></label>
                    </div>
                    <%
                            } // Cierre del for (Opcion opcion : opciones)
                        }
                    %>
                </td>
                <td>
                    <%
                        if (respuesta != null) {
                            int opcionId = Integer.parseInt(respuesta.getRespuesta());
                            Opcion respuestaSeleccionada = opciones.stream()
                                    .filter(o -> o.getIdOpcion() == opcionId)
                                    .findFirst()
                                    .orElse(null);
                            if (respuestaSeleccionada != null) {
                                System. out.print(respuestaSeleccionada.getOpcion());
                            }
                        }
                    %>
                </td>
            </tr>
            <%
                } // Cierre del for (Preguntas pregunta : preguntas)
            %>
            </tbody>
        </table>

        <button type="submit" class="btn btn-primary">Enviar Calificación</button>
    </form>

    <a href="index.jsp" class="btn btn-primary mt-3">Volver al inicio</a>
    <%
        } else {
            System.out.println("Datos insuficientes para mostrar el formulario.");
        }
    %>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
