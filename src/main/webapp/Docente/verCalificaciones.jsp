<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.CalificacionDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.ExamenDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Calificacion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calificaciones por Docente</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vistas.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables.css">
    <style>
        .calificacion-verde {
            color: green;
            font-weight: bold;
        }
        .calificacion-amarillo {
            color: gold;
            font-weight: bold;
        }
        .calificacion-rojo {
            color: red;
            font-weight: bold;
        }
        .thead-dark th {
            color: black !important;
            background-color: #f8f9fa !important;
        }
        .header-card {
            display: flex;
            align-items: center;
            justify-content: space-between;
            background: #CDFFF4;
            border-radius: 10px;
            padding: 10px 20px;
            margin-bottom: 20px;
        }
        .header-card img {
            width: 68px;
        }
        .btn-cal {
            background-color: #FED8D8;
            border-radius: 22px;
            color: black;
            padding: 5px 20px;
            font-weight: 700;
            border: none;
            cursor: pointer;
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
        <div class="header-card">
            <img src="${pageContext.request.contextPath}/img/calificaciones.png" alt="Calificaciones">
            <h2>Calificaciones por Docente</h2>
            <button type="button" class="btn btn-conf" onclick="location.href='indexDocente.jsp'">Regresar</button>
        </div>
        <div class="card-body">
            <table id="calificacionesTable" class="table table-custom">
                <thead class="thead-dark">
                <tr>

                    <th>Matrícula Alumno</th>
                    <th>Examen</th>
                    <th>Calificación</th>
                    <th>Fecha</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <%
                    String matriculaDocente = (String) session.getAttribute("matriculaDocente");
                    if (matriculaDocente != null) {
                        try {
                            CalificacionDao calificacionDao = new CalificacionDao();
                            ExamenDao examenDao = new ExamenDao();
                            List<Calificacion> calificaciones = calificacionDao.obtenerCalificacionesPorDocente(matriculaDocente);
                            for (Calificacion calificacion : calificaciones) {
                                double nota = calificacion.getCalificacion();
                                String colorClass = "";
                                if (nota >= 80) {
                                    colorClass = "calificacion-verde";
                                } else if (nota >= 51) {
                                    colorClass = "calificacion-amarillo";
                                } else {
                                    colorClass = "calificacion-rojo";
                                }
                %>
                <tr>

                    <td><%= calificacion.getMatriculaAlumno() %></td>
                    <td><%= examenDao.obtenerNombreExamenPorId(calificacion.getIdExamen()) %></td>
                    <td class="<%= colorClass %>"><%= nota %></td>
                    <td><%= calificacion.getFecha() %></td>
                    <td>
                        <form action="calificarExamen" method="get">
                            <input type="hidden" name="id_examen" value="<%= calificacion.getIdExamen() %>">
                            <input type="hidden" name="id_calificacion" value="<%= calificacion.getIdCalificacion()%>">
                            <input type="hidden" name="matricula_estudiante" value="<%= calificacion.getMatriculaAlumno() %>">
                            <button type="submit" class="btn btn-cal">Calificar</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error: " + e.getMessage());
                    }
                } else {
                %>
                <tr>
                    <td colspan="6" class="text-center">No hay calificaciones disponibles.</td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- DataTables JS -->
<script src="${pageContext.request.contextPath}/JS/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/JS/dataTables.bootstrap4.min.js"></script>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        $('#calificacionesTable').DataTable({
            "language": {
                "url": '${pageContext.request.contextPath}/JS/es-MX.json'
            }
        });
    });
</script>
</body>
</html>
