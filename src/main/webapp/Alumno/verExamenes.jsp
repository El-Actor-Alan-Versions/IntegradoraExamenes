<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Examen, mx.edu.utez.integradiratjuans.model.Clase" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.ClaseDao" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ver Exámenes</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">Exámenes Disponibles</h1>

    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Fecha de Aplicación</th>
            <th>Fecha de Cierre</th>
            <th>Grado_Grupo</th>
            <th>Realizar Examen</th>

        </tr>
        </thead>
        <tbody>
        <%
            List<Examen> examenes = (List<Examen>) request.getAttribute("examenes");
            List<Clase> clases = (List<Clase>) request.getAttribute("clases");
            ClaseDao  claseDao = new ClaseDao();
            String grado_grupo = claseDao.getGrupoNameByIdClase((Integer) session.getAttribute("clase"));

            if (examenes != null && !examenes.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (Examen examen : examenes) {
                    Timestamp fechaAplicacion = examen.getFecha_aplicacion();
                    Timestamp fechaCierre = examen.getFecha_cierre();
                    String gradoGrupo = grado_grupo;

                    if (clases != null) {
                        for (Clase clase : clases) {
                            if (clase.getId_clase() == examen.getId_clase()) {
                                gradoGrupo = clase.getGradoGrupo();
                                break;
                            }
                        }
                    }
        %>
        <tr>
            <td><%= examen.getId_examen() %></td>
            <td><%= examen.getNombre() %></td>
            <td><%= fechaAplicacion != null ? sdf.format(fechaAplicacion) : "N/A" %></td>
            <td><%= fechaCierre != null ? sdf.format(fechaCierre) : "N/A" %></td>
            <td><%= gradoGrupo %></td>
            <td>
                <form action="IniciarExamenServlet" method="post" class="d-inline">
                    <input type="hidden" name="id_examen" value="<%= examen.getId_examen() %>">
                    <button type="submit" class="btn btn-success btn-sm">Iniciar</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5" class="text-center">No hay exámenes disponibles</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <a href="index.jsp" class="btn btn-primary">Volver al inicio</a>
</div>

<!-- Enlace a Bootstrap JS y dependencias (opcional) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
