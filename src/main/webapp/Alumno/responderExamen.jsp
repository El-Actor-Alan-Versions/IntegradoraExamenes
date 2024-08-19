<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Preguntas" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Opcion" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.ExamenDao" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Responder Examen</title>
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
<!-- Incluir el JS de Bootstrap y dependencias -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        $('.dropdown-toggle').dropdown();
    });
</script>
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
    <div id="card-title" class="card">
        <div class="header-card"></div>
        <div class="card-body">
            <div class="card-text"></div>
            <%
                ExamenDao examenDao = new ExamenDao();
                Integer idExamen = (Integer) session.getAttribute("idExamen");
                String nombreExamen = examenDao.obtenerNombreExamenPorId(idExamen);
            %>
            <p class="title-prg"> <%= nombreExamen %></p>
        </div>
    </div>

    <div class="card">
        <div class="header-card"></div>
        <div class="card-body">
            <div class="card-text">
                <%
                    String descripcion = examenDao.obtenerDescripcionExamenPorId((Integer) session.getAttribute("idExamen"));
                %>
                <h6><%= descripcion %></h6>
            </div>
        </div>
    </div>

    <form action="EnviarRespuestas" method="post">
        <input type="hidden" name="id_examen" value="<%= session.getAttribute("idExamen") %>">

        <%
            List<Preguntas> preguntas = (List<Preguntas>) request.getAttribute("preguntas");
            if (preguntas != null && !preguntas.isEmpty()) {
                for (Preguntas pregunta : preguntas) {
                    List<Opcion> opciones = pregunta.getOpciones();
        %>

        <div class="card">
            <div class="header-card"></div>
            <div class="card-body">
                <p class="input"><%= pregunta.getTexto() %></p>
                <div class="card-text">
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
                        <input class="form-check-input" type="checkbox" name="pregunta_<%= pregunta.getIdPregunta() %>" value="<%= opcion.getOpcion() %>">
                        <label class="form-check-label">
                            <%= opcion.getOpcion() %>
                        </label>
                    </div>
                    <%
                        }
                    } else if ("abierta".equals(tipoPregunta)) {
                    %>
                    <div class="form-group">
                        <input type="text" class="form-control" name="pregunta_<%= pregunta.getIdPregunta() %>" placeholder="Escribe tu respuesta aquí...">
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

        <button type="submit" class="btn btn-enviar">Enviar Respuestas</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
