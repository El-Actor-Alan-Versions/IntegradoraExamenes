<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cambiar Contraseña</title>
    <!-- Agrega Bootstrap CSS si no está incluido -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="text-center mt-5">Cambiar contraseña</h2>
            <form method="post" action="updateContra">
                <div class="form-group">
                    <label for="contraseña">Nueva Contraseña: </label>
                    <input type="password" id="contraseña" name="contraseña" class="form-control" required>
                </div>
                <input type="hidden" name="codigo" value="<%= request.getParameter("codigo") %>">
                <div class="text-center">
                    <input type="submit" class="btn btn-dark btn-block" value="Cambiar">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Agrega Bootstrap JS si no está incluido -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
