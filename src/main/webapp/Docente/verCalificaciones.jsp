<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Calificacion" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.CalificacionDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.ExamenDao" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calificaciones por Docente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css"/>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');

        body {
            font-family: 'PT Sans';
            color: rgb(17, 16, 16);
            background-color: #EEEEEE;
            margin: 0;
            padding: 0;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
        }

        .container {
            background: #b8eac2;
            width: 100%;
            align-items: center;
            margin-top: 20px;
            padding: 20px 0;
        }

        .card {
            width: 100%;
            background: none;
            border: none;
            align-items: center;
            margin-top: 10px;
        }

        .card-body {
            background: white;
            width: 80%;
            overflow-x: auto;
            border-radius: 20px;
            margin: 20px auto;
            padding: 20px;
        }

        .table-custom {
            width: 100%;
            background: white;
            border-radius: 15px;
            border-collapse: collapse;
            text-align: center;
            margin: 20px auto;
        }

        .table-custom th, .table-custom td {
            padding: 10px;
            border: 1px solid #ddd;
        }

        .table-custom th {
            background-color: #85C5B7;
            font-weight: bold;
        }

        .table-custom tbody tr:hover {
            background-color: #f1f1f1;
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

        .header-card {
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: 90%;
            background: #CDFFF4;
            margin-top: 15px;
            border-radius: 10px;
            padding: 10px 20px;
        }

        .title {
            font-size: 24px;
            font-weight: 600;
            margin: 0;
        }

        @media (max-width: 600px) {
            .header-card {
                flex-direction: column;
                align-items: center;
                text-align: center;
            }

            .header-card img,
            .header-card h1 {
                margin: 5px 0;
                width: auto;
            }
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
            <h1 class="title">Calificaciones de los Alumnos</h1>
        </div>
        <div class="card-body">
            <%
                String matriculaDocente = (String) session.getAttribute("matriculaDocente");
                if (matriculaDocente != null) {
                    CalificacionDao calificacionDao = new CalificacionDao();
                    ExamenDao examenDao = new ExamenDao();
                    List<Calificacion> calificaciones = calificacionDao.obtenerCalificacionesPorDocente(matriculaDocente);
            %>
            <table id="calificacionesTable" class="table table-custom">
                <thead>
                <tr>
                    <th>ID Calificación</th>
                    <th>Matrícula Alumno</th>
                    <th>Examen</th>
                    <th>Calificación</th>
                    <th>Fecha</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (calificaciones != null && !calificaciones.isEmpty()) {
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
                    <td><%= calificacion.getIdCalificacion() %></td>
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
            <%
            } else {
            %>
            <p>Error: No se encontró la matrícula del docente en la sesión.</p>
            <%
                }
            %>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- DataTables JS -->
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
<!-- Inicialización de DataTables -->
<script>
    $(document).ready(function() {
        $('#calificacionesTable').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.13.4/i18n/es-MX.json"
            }
        });
    });
</script>
</body>
</html>
