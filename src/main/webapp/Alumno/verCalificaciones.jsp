<%@ page import="mx.edu.utez.integradiratjuans.dao.CalificacionDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Calificacion" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calificaciones</title>
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
            color: black !important; /* Cambia el color del texto a negro */
            background-color: #f8f9fa !important; /* Cambia el color de fondo si lo deseas */
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
    <div class="card">
        <div class="header-card">
            <img src="../img/calificaciones.png" width="68">
            <h2>Calificaciones</h2>
            <button type="button" class="btn btn-conf" onclick="location.href='indexAlumno.jsp'">Regresar a Inicio</button>
        </div>
        <div class="card-body">
            <table id="calificacionesTable" class="table table-custom">
                <thead class="thead-dark">
                <tr>
                    <th>Matrícula Alumno</th>
                    <th>Nombre Examen</th>
                    <th>Calificación</th>
                    <th>Fecha</th>
                </tr>
                </thead>
                <tbody>
                <%
                    String matriculaAlumno = (String) session.getAttribute("matriculaAlumno");
                    try {
                        CalificacionDao dao = new CalificacionDao();
                        List<Calificacion> calificaciones = dao.getCalificacionesConNombreExamen(matriculaAlumno);
                        for (Calificacion c : calificaciones) {
                            double calificacion = c.getCalificacion();
                            String colorClass = "";
                            if (calificacion >= 80) {
                                colorClass = "calificacion-verde";
                            } else if (calificacion >= 51) {
                                colorClass = "calificacion-amarillo";
                            } else {
                                colorClass = "calificacion-rojo";
                            }
                %>
                <tr>
                    <td><%= c.getMatriculaAlumno() %></td>
                    <td><%= c.getNombreExamen() %></td>
                    <td class="<%= colorClass %>"><%= calificacion %></td>
                    <td><%= c.getFecha() %></td>
                </tr>
                <%
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error: " + e.getMessage());
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Librerías de DataTables -->
<script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath}/JS/datatables.js"></script>
<script src="${pageContext.request.contextPath}/JS/dataTables.bootstrap5.js"></script>
<script src="${pageContext.request.contextPath}/JS/es-MX.json"></script>

<script>
    // Inicializar DataTables en la tabla de calificaciones
    document.addEventListener('DOMContentLoaded', () => {
        const table = document.getElementById('calificacionesTable');
        new DataTable(table, {
            language: {
                url: '${pageContext.request.contextPath}/JS/es-MX.json'
            }
        });
    });
</script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
