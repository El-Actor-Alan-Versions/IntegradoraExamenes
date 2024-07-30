<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Examen" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Clase" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ver Exámenes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Exámenes</h2>

    <!-- Formulario de filtro -->
    <form method="post" action="VerExamenesServlet">
        <div class="form-group">
            <label for="id_clase">Clase</label>
            <select id="id_clase" class="form-control" name="id_clase" required>
                <option value="">Seleccione...</option>
                <%
                    List<Clase> clases = (List<Clase>) session.getAttribute("clases");
                    if (clases != null) {
                        for (Clase clase : clases) {
                %>
                <option value="<%= clase.getId_clase() %>"><%= clase.getGradoGrupo() %></option>
                <%
                    }
                } else {
                %>
                <option value="">No hay clases disponibles</option>
                <%
                    }
                %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mt-3">Filtrar</button>
    </form>

    <!-- Tabla de exámenes -->
    <table class="table table-striped table-hover mt-4">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Fecha de Aplicación</th>
            <th>Fecha de Cierre</th>
            <th>Grado_Grupo</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Examen> examenes = (List<Examen>) request.getAttribute("examenes");
            if (examenes != null && !examenes.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (Examen examen : examenes) {
                    Timestamp fechaAplicacion = examen.getFecha_aplicacion();
                    Timestamp fechaCierre = examen.getFecha_cierre();
                    String gradoGrupo = "N/A";
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
                <form action="EditarExamenServlet" method="post" class="d-inline">
                    <input type="hidden" name="id_examen" value="<%= examen.getId_examen() %>">
                    <button type="submit" class="btn btn-warning btn-sm">Modificar</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6" class="text-center">No hay exámenes disponibles</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
