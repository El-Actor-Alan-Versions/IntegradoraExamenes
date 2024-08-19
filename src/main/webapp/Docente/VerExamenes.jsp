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
    <title>Ver Exámenes</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vistas.css">
    <style>
        .btn-filtrar {
            background-color: #007bff; /* Cambia el color según tus preferencias */
            color: white;
            border: none;
            margin-top: 10px; /* Espacio superior opcional */
        }

        .form-container {
            margin-bottom: 20px;
        }

        .form-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .form-group select {
            flex: 1; /* Esto permite que el select ocupe el espacio disponible */
        }

        .form-group button {
            margin-left: 10px; /* Espacio opcional entre el select y el botón */
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
            <h2>Exámenes Disponibles</h2>
            <button type="button" class="btn btn-conf" onclick="location.href='indexDocente.jsp'">Volver</button>
        </div>
        <div class="card-body">
            <div class="form-container">
                <form method="post" action="VerExamenesServlet">
                    <div class="form-group">
                        <label for="id_clase">Clase</label>
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
                        <p>
                        <button type="submit" class="btn btn-filtrar">Filtrar</button>
                    </div>
                </form>
            </div>

            <table id="example" class="table table-custom">
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Fecha de Aplicación</th>
                    <th>Fecha de Cierre</th>
                    <th>Grado_Grupo</th>

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
</div>

<script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath}/JS/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/JS/datatables.js"></script>
<script src="${pageContext.request.contextPath}/JS/dataTables.bootstrap5.js"></script>
<script src="${pageContext.request.contextPath}/JS/es-MX.json"></script>
<script>
    // Inicializar DataTables en la tabla objetivo
    document.addEventListener('DOMContentLoaded', () => {
        const table = document.getElementById('example');
        new DataTable(table, {
            language: {
                url: '${pageContext.request.contextPath}/JS/es-MX.json'
            }
        });
    });

    function confirmarEliminacion() {
        return confirm('¿Estás seguro de que deseas eliminar este examen?');
    }
</script>
</body>
</html>
