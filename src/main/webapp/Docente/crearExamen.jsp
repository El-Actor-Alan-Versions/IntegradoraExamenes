    <%@ page import="java.util.List" %>
    <%@ page import="mx.edu.utez.integradiratjuans.model.Clase" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Crear Examen</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/crearExamen.css">

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
        <div class="card-custom mt-5">
            <div class="card-title">
                <p class="text-center">CREACIÓN DE EXÁMEN</p>
            </div>
            <div class="card-body">
                <form action="CrearExamenServlet" method="post">
                    <div class="label-input-group">
                        <label for="nombre" class="form-label"  >Nombre del Examen</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" maxlength="120" required>
                    </div>
                    <div class="label-input-group">
                        <label for="descripcion" class="form-label">Descripción</label>
                        <textarea class="form-control" id="descripcion" name="descripcion"  maxlength="256" required></textarea>
                    </div>
                    <div class="label-input-group">
                        <label for="fecha_aplicacion" class="form-label">Fecha de Aplicación</label>
                        <input type="datetime-local" class="form-control" id="fecha_aplicacion" name="fecha_aplicacion" required>
                    </div>
                    <div class="label-input-group">
                        <label for="fecha_cierre" class="form-label">Fecha de Cierre</label>
                        <input type="datetime-local" class="form-control" id="fecha_cierre" name="fecha_cierre" required>
                    </div>
                    <div class="label-input-group">
                        <label for="id_clase" class="form-label">Clase</label>
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
                    <button type="submit" class="btn  btn-right">Crear Examen</button>
                </form>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
    </html>
