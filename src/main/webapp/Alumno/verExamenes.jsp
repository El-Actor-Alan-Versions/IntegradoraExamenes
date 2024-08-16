<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Examen, mx.edu.utez.integradiratjuans.model.Clase" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.ClaseDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.ExamenDao" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ver Exámenes</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vistas.css">

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
<div class="container ">
    <div class="card">
        <div class="header-card">
            <img src="../img/calificaciones.png" width="68">
            <h2>Exámenes Disponibles</h2>
            <button type="button" class="btn btn-conf" onclick="location.href='indexAlumno.jsp'">Volver</button>
        </div>
        <div class="card-body">
            <table class="table  table-custom">
                <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Fecha de Aplicación</th>
                    <th>Fecha de Cierre</th>
                    <th>Grado_Grupo</th>
                    <th class="column-actualizar">Realizar Examen</th>

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
                        <form action="CargarExamen" method="post" >
                            <input type="hidden" name="id_examen" value="<%= examen.getId_examen() %>">
                            <button type="submit" class="btn btn btn-new">Iniciar</button>
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
        </div>
    </div>
</div>

<!-- Enlace a Bootstrap JS y dependencias (opcional) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>