<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.AlumnoDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Alumno" %>

<%

    String matriculaSesion = (String) session.getAttribute("matriculaAlumno");

%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cambio de Contraseña</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registroUsuarios.css">
</head>

<!-- Incluir el JS de Bootstrap y dependencias -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<body>
<div id="navbar"></div>
<script>
    // Cargar el navbar de forma asíncrona
    fetch('navbar.jsp')
        .then(response => response.text())
        .then(data => {
            // Insertar el contenido del navbar
            document.getElementById('navbar').innerHTML = data;

            // Inicializar el dropdown de Bootstrap después de que el navbar haya sido cargado
            $('.dropdown-toggle').dropdown();
        });
</script>

<div class="container">
    <div class="form text-center d-flex justify-content-center align-items-center ">
        <div class="text-center">
            <form action="cambiarContraAlumno" class="form border-0 text-center" method="post">
                <img src="../IMG2/login.png" alt="profile icon" class="profile-icon" width="174px">
                <input type="hidden" name="matriculaAlumno" value="<%= matriculaSesion != null ? matriculaSesion : "" %>" />
                <div class="form-group mb-3">
                    <input type="password" class="form-control rounded-pill text-center" name="nuevaContraseña" placeholder="Nueva contraseña" required />
                </div>

                <button type="submit" class="btn rounded-pill">Cambiar Contraseña</button>
            </form>
        </div>
    </div>
</div>

<!-- Incluir el JS de Bootstrap y dependencias -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
