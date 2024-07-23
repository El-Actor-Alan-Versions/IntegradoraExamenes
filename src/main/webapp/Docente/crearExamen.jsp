<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Examen</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Crear Examen</h2>
    <form id="crearExamen" method="POST" action="crearExamen">
        <!-- Campos del formulario -->
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre del Examen</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>
        <div class="mb-3">
            <label for="fecha_aplicacion" class="form-label">Fecha de Aplicación</label>
            <input type="datetime-local" class="form-control" id="fecha_aplicacion" name="fecha_aplicacion" required>
        </div>
        <div class="mb-3">
            <label for="fecha_cierre" class="form-label">Fecha de Cierre</label>
            <input type="datetime-local" class="form-control" id="fecha_cierre" name="fecha_cierre" required>
        </div>
        <div class="mb-3">
            <label for="id_clase" class="form-label">ID de la Clase</label>
            <input type="number" class="form-control" id="id_clase" name="id_clase" required>
        </div>
        <button type="submit" class="btn btn-primary m-2">Crear</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
