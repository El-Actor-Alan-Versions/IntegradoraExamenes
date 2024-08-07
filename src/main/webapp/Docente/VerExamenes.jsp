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
        margin-top: 20px; /* Margen superior añadido */
    }

    .card{
        width: 100%;
        height: 100%;
        background: none;
        border: none;
        align-items: center;
        margin-top: 10px;
    }

    .card-body{
        background: white;
        width: 80%;
        overflow-x: auto;
        border-radius: 20px;
        margin-top: 20px;
    }

    .header-card{
        width: 90%;
        background: #CDFFF4;
        margin-top: 15px;
        border-radius: 10px;
    }

    .table-custom {
        padding: 30px 50%;
        text-align: center;
        background: white;
        border-radius: 15px;
        margin: 20px auto;
        width: 90%;
        height: auto;
        background: white;
        border-collapse: collapse;
        margin-left: 20%;
    }

    h2{
        margin-left: 20px;
    }

    .form-select{
        width: 300px;
        font-weight: 600;
        border-radius: 25px;
        box-sizing: border-box;
        max-width: 50%;
        background-color:#85C5B7;
        text-align: center;
    }

    .input-group{
        border-radius: 10px;
        margin-left: 30px;
    }

    .label-input-group{
        display: flex;
        align-items: center;
        margin-bottom: 15px;
        width: 100%;
        max-width: 600px;
    }

    label {
        font-family: 'PT Sans';
        font-weight: bold;
        border-radius: 25px;
        width: 230px;
        text-align: right;
        margin-right: 10px;
    }

    .btn-filtrar{
        border-radius: 20px;
        margin-left: 5%;
        margin-top: 10px;
        width: 10%;
        font-weight: 600;
        background: #b7f3c4;
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
<div class="container ">
    <div class="card">
        <div class="header-card">
            <h2 >Exámenes</h2>
            <div class="container-custom">
                <form method="post" action="VerExamenesServlet">
                    <div class="form-group">
                        <div class="input-group">
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
                        </div>
                        </div>
                    <button type="submit" class="btn btn-filtrar ">Filtrar</button>
                </form>

            </div>
        </div>
    </div>
    <!-- Tabla de exámenes -->
    <div class="card-body">
        <table class="table table-custom">
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
                    <form action="EditarExamenServlet" method="post" >
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
