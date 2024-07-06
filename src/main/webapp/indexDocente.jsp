<%--
  Created by IntelliJ IDEA.
  User: walge
  Date: 23/06/2024
  Time: 07:00 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestor de Exámenes</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<!--Navbar-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <img src="img/iconoRobado.png" width="48" alt="icono">
    <a class="navbar-brand" >Formularios</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="indexDocente.jsp">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="gestionarExamenes.jsp">Crear Examen</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="jsp/gestionarCalificaciones.jsp">Gestionar Calificaciones</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="jsp/editarPerfil.jsp">Modificar Perfil</a>
            </li>
        </ul>
    </div>
</nav>

<!--Contenido-->
<section class="options-section py-5">
    <div class="container">
        <div class="row">
            <div class="col-md-4 text-center">
                <div class="card">
                    <div class="card-body">
                        <img src="img/añadir (2).png" class="text-center" width="68" alt="Examen">
                        <h5 class="card-title">Crear Examen</h5>
                        <p class="card-text">Diseña y publica nuevos exámenes fácilmente.</p>
                        <a href="crearExamen.jsp" class="btn btn-primary rounded-pill">Crear</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 text-center">
                <div class="card">
                    <div class="card-body">
                        <img src="img/calificaciones.png" class="text-center" width="68" alt="calificaciones">
                        <h5 class="card-title">Gestionar Calificaciones</h5>
                        <p class="card-text">Administra y revisa las calificaciones de los estudiantes.</p>
                        <a href="gestionarExamenes.jsp" class="btn btn-primary rounded-pill">Gestionar</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 text-center">
                <div class="card">
                    <div class="card-body">
                        <img src="img/perfil (2).png" class="text-center" width="68" alt="usuario">
                        <h5 class="card-title">Modificar Perfil</h5>
                        <p class="card-text">Actualiza tu información personal y ajustes de cuenta.</p>
                        <a href="jsp/editarPerfil.jsp" class="btn btn-primary rounded-pill">Modificar</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="bg-light text-center py-4">

</footer>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- Custom JS -->
<script src="js/scripts.js"></script>
</body>
</html>
