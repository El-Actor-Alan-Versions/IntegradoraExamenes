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
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css"/>
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

        .title{
            font-size: 28px;
            font-weight: 500;
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

        form {
            display: flex;
            align-items: center;
            justify-content: flex-end;
            width: 100%;
            margin-right: 10%;
        }

        .label-input-group {
            margin-right: 10px;
            font-weight: bold;
        }

        .form-select {
            border-radius: 25px;
            background-color: #6fb5a4;
            padding: 5px 10px;
            margin-right: 10px;
            max-width: 600px;
            width: 100%;
        }

        .btn-filtrar {
            background-color: #9EEB92;
            border-radius: 50px;
            color: black;
            padding: 5px 20px;
            font-weight: 700;
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
            margin: 20px auto;
            background: white;
            border-radius: 15px;
            border-collapse: collapse;
            text-align: center;
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

        .btn-del {
            background-color: #FF6B6B;
            border-radius: 20px;
            padding: 5px 10px;
            font-weight: 700;
            color: white;
            border: none;
            cursor: pointer;
        }

        .text-center {
            text-align: center;
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
            <p class="title">Exámenes Disponibles</p>
            <form method="post" action="VerExamenesServlet">
                <label for="id_clase" class="label-input-group">Clase</label>
                <select id="id_clase" class="form-select" name="id_clase" required>
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
                <button type="submit" class="btn btn-filtrar">Filtrar</button>
            </form>
        </div>
    </div>
    <!-- Tabla de exámenes -->
    <div class="card-body">
        <table id="examenesTable" class="table table-custom">
            <thead>
            <tr>
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
                <td><%= examen.getNombre() %></td>
                <td><%= fechaAplicacion != null ? sdf.format(fechaAplicacion) : "N/A" %></td>
                <td><%= fechaCierre != null ? sdf.format(fechaCierre) : "N/A" %></td>
                <td><%= gradoGrupo %></td>
                <td>
                    <form action="EditarExamenServlet" method="post">
                        <input type="hidden" name="id_examen" value="<%= examen.getId_examen() %>">
                        <button type="submit" class="btn btn-del">Modificar</button>
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
        $('#examenesTable').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.13.4/i18n/es-MX.json"
            }
        });
    });
</script>
</body>
</html>
