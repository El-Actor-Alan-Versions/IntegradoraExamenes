<%--
  Created by IntelliJ IDEA.
  User: walge
  Date: 23/06/2024
  Time: 06:35 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link  rel="stylesheet"  href="css/indexAdmin.css">


</head>
</head>
<body>
<!--Navbar-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <img src="img/iconoRobado.png" width="48" alt="icono">
        <br>
        <a class="navbar-brand">Formularios</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="indexAdmin.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="registrarPersona.jsp">Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="jsp/gestionarGrupos.jsp">Grupos</a>
                </li>
            </ul>
        </div>
    </div>
</nav>



<!--Contenido-->
<section class="options-section py-5">
    <div class="container">
        <div class="row">
            <div class="col-md-6 text-center">
                <div class="card">
                    <div class="card-body">
                        <img src="img/usuarios.png" class="text-center" width="68" alt="Usuarios">
                        <h5 class="card-title">Registrar Usuarios</h5>
                        <p class="card-text">Administra los usuarios de la plataforma.</p>
                        <a href="registrarPersona.jsp" class="btn btn-primary rounded-pill">Registrar</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6 text-center">
                <div class="card">
                    <div class="card-body">
                        <img src="img/grupos.png" class="text-center" width="68" alt="Grupos">
                        <h5 class="card-title">Gestionar Grupos</h5>
                        <p class="card-text">Administra los grupos de usuarios.</p>
                        <a href="jsp/gestionarGrupos.html" class="btn btn-primary rounded-pill">Gestionar</a>
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
