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
</head>
<body>
<div class="container">
    <h2>Crear Examen</h2>
    <form action="CrearExamenServlet" method="post">
        <div class="form-group">
            <label for="nombre">Nombre del Examen</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
        </div>
        <div class="form-group">
            <label for="fecha_aplicacion">Fecha de Aplicación</label>
            <input type="datetime-local" class="form-control" id="fecha_aplicacion" name="fecha_aplicacion" required>
        </div>
        <div class="form-group">
            <label for="fecha_cierre">Fecha de Cierre</label>
            <input type="datetime-local" class="form-control" id="fecha_cierre" name="fecha_cierre" required>
        </div>
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
        <button type="submit" class="btn btn-primary">Crear Examen</button>
    </form>
</div>
<script src="path/to/jquery.min.js"></script>
<script src="path/to/bootstrap.min.js"></script>
</body>
</html>
