<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Calificacion" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.CalificacionDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.ExamenDao" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calificaciones por Docente</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<style>
    @import url('https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');

    body {
        font-family: 'PT Sans';
        color: rgb(17, 16, 16);
        justify-content: center;
        align-items: center;
        background-color: #EEEEEE;
        width: 100%;
        height: 100%;
        margin: 0;
        padding: 0;
        text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
    }

    .container {
        background: #b8eac2;
        font-family: 'PT Sans', sans-serif;
        width: 100%;
        align-items: center;
        margin-top: 20px;
    }

    .card {
        width: 100%;
        height: 100%;
        background: none;
        border: none;
        align-items: center;
        margin-top: 10px;
    }

    .card-body {
        background: white;
        width: 90%;
        overflow-x: auto;
        border-radius: 20px;
        margin-top: 20px;
        padding: 20px;
    }

    .table-custom {
        padding: 30px 50%;
        text-align: center;
        background: white;
        border-radius: 15px;
        margin: 20px auto;
        width: 90%;
        border-collapse: collapse;
    }

    .table-custom th,
    .table-custom td {
        border: 2px solid black;
        padding: 10px;
        white-space: nowrap;
    }
    .header-card {
        background-color: #CDFFF4;
        padding: 10px;
        border-radius: 8px;
        display: flex;
        align-items: center;
        width: 90%;
        height: 20%;
        max-width: 900px;
        margin: 10px auto;
    }

    .title {
        margin-left: 10px;
        font-size: 1.5rem; /* Tamaño ajustado del título */
    }

    .header-card img {
        margin-right: 10px;
        width: 68px; /* Tamaño ajustado de la imagen */
        height: auto;
    }

    .btn-cal {
        width: 150px;
        border-radius: 22px;
        white-space: nowrap;
        background: #FED8D8;
        font-weight: 700;
    }

    @media (max-width: 600px) {
        .header-card {
            flex-direction: column;
            align-items: center;
            text-align: center;
        }

        .header-card img,
        .header-card h2,
        .btn-cal {
            margin: 5px 0;
            width: auto;
        }

        .btn-cal {
            width: 100%;
            max-width: 200px;
        }
    }

</style>
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
            <table class="table table-custom">
                <thead>
                <tr>
                    <th>ID Calificación</th>
                    <th>Matrícula Alumno</th>
                    <th>Examen</th>
                    <th>Calificación</th>
                    <th>Fecha</th>
                    <th>Acciones</th> <!-- Nueva columna para acciones -->
                </tr>
                </thead>
                <tbody>
                <%
                    if (calificaciones != null && !calificaciones.isEmpty()) {
                        for (Calificacion calificacion : calificaciones) {
                %>
                <tr>
                    <td><%= calificacion.getIdCalificacion() %></td>
                    <td><%= calificacion.getMatriculaAlumno() %></td>
                    <td><%= examenDao.obtenerNombreExamenPorId(calificacion.getIdExamen()) %></td>
                    <td><%= calificacion.getCalificacion() %></td>
                    <td><%= calificacion.getFecha() %></td>
                    <td>
                        <form action="calificarExamen" method="get">
                            <input type="hidden" name="id_examen" value="<%= calificacion.getIdExamen() %>">
                            <% System.out.println(calificacion.getIdExamen()); %>
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
                    <td colspan="6">No hay calificaciones disponibles.</td>
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
</body>
</html>