<!--
  Created by IntelliJ IDEA.
  User: esqui
  Date: 23/06/2024
  Time: 07:00 p. m.
  To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexAdmin.css">

    <title>Admin</title>
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
    <div class="row justify-content-center">
        <!-- Tarjeta Gestionar Alumnos -->
        <div class="col-md-3 col-sm-6 col-12 text-center mb-4">
            <a href="verAlumnos.jsp" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/mirarCalificaciones.png" class="text-center" width="68">
                        <h5 class="card-title">Gestionar Alumnos</h5>
                        <p class="card-text">Gestiona el alumnado, puedes actualizar el estado de los archivos y estadísticas.</p>
                    </div>
                </div>
            </a>
        </div>
        <!-- Tarjeta Gestionar Profesores -->
        <div class="col-md-3 col-sm-6 col-12 text-center mb-4">
            <a href="verDocente.jsp" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/mirarCalificaciones.png" class="text-center" width="68">
                        <h5 class="card-title">Gestionar Profesores</h5>
                        <p class="card-text">Edita el personal, puedes actualizar su estado.</p>
                    </div>
                </div>
            </a>
        </div>
        <!-- Tarjeta Gestionar Grupos -->
        <div class="col-md-3 col-sm-6 col-12 text-center mb-4">
            <a href="verGrupos.jsp" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/mirarCalificaciones.png" class="text-center" width="68">
                        <h5 class="card-title">Gestionar Grupos</h5>
                        <p class="card-text">Edita el personal, puedes actualizar su estado.</p>
                    </div>
                </div>
            </a>
        </div>
        <!-- Tarjeta Gestionar Clases -->
        <div class="col-md-3 col-sm-6 col-12 text-center mb-4">
            <a href="verClases.jsp" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/mirarCalificaciones.png" class="text-center" width="68">
                        <h5 class="card-title">Gestionar Clases</h5>
                        <p class="card-text">Gestiona tus clases y creación, puedes modificar y actualizar.</p>
                    </div>
                </div>
            </a>
        </div>
        <!-- Tarjeta Gestionar Carreras -->
        <div class="col-md-3 col-sm-6 col-12 text-center mb-4">
            <a href="verCarreras.jsp" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/mirarCalificaciones.png" class="text-center" width="68">
                        <h5 class="card-title">Gestionar Carreras</h5>
                        <p class="card-text">Gestiona tu carrera con respecto a tu plan académico.</p>
                    </div>
                </div>
            </a>
        </div>
        <!-- Tarjeta Gestionar Divisiones -->
        <div class="col-md-3 col-sm-6 col-12 text-center mb-4">
            <a href="verDivisiones.jsp" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/mirarCalificaciones.png" class="text-center" width="68">
                        <h5 class="card-title">Gestionar Divisiones</h5>
                        <p class="card-text">Gestiona las divisiones que conforman parte de tu universidad.</p>
                    </div>
                </div>
            </a>
        </div>
        <!-- Tarjeta Gestionar Materias -->
        <div class="col-md-3 col-sm-6 col-12 text-center mb-4">
            <a href="verMaterias.jsp" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/mirarCalificaciones.png" class="text-center" width="68">
                        <h5 class="card-title">Gestionar Materias</h5>
                        <p class="card-text">Gestiona las materias que se impartirán en la universidad.</p>
                    </div>
                </div>
            </a>
        </div>
        <!-- Tarjeta Asignacion de clase -->
        <div class="col-md-3 col-sm-6 col-12 text-center mb-4">
            <a href="verAdmin.jsp" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/asignacionClases.png" class="text-center" width="68">
                        <h5 class="card-title">Gestionar Administradores</h5>
                        <p class="card-text">Registro, eliminación y actualización de los datos de los administradores</p>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>
</body>
</html>
