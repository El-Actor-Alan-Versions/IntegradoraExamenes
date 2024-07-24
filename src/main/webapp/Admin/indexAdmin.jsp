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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Admin</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');

        body {
            font-family: 'PT Sans';
            font-weight: bold;
            color: black;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .card {
            right: 10px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #fff;
            transition: transform 0.2s;
            border: 2px solid black;
            background-color: #CDFFF4;
            text-align: center;
            width: 100%;
            height: 220px;
            margin: 10px;
        }

        .card:hover {
            transform: translateY(-10px);
            cursor: pointer;
        }

        .card img {
            width: 100px;
            height: 100px;
            margin-bottom: 10px;
        }

        h5 {
            font-family: 'PT Sans';
            font-weight: bold;
            color: black;
        }

        .card-text {
            font-family: 'PT Sans';
            font-weight: bold;
            color: black;
            margin-bottom: 20px;
        }

        .container {
            background-color: #CDFFF4;
            margin-top: 20px;
            border-radius: 15px;
            max-width: 98%;
            width: 1367px;
            height: auto;
        }

        .col-md-3 {
            max-width: 25%;
            flex: 0 0 25%;
        }

        .button {
            text-decoration: none;
            color: inherit;
        }

        footer {
            margin-top: 15px;
        }

        .invisible-card {
            visibility: hidden;
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
<div class="container">
    <div class="row justify-content-center">
        <!-- Tarjeta Gestionar Alumnos -->
        <div class="col-md-3 col-sm-6 text-center mb-4">
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
        <div class="col-md-3 col-sm-6 text-center mb-4">
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
        <div class="col-md-3 col-sm-6 text-center mb-4">
            <a href="verGrupos.jsp" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/mirarCalificaciones.png" class="text-center" width="68">
                        <h5 class="card-title">Gestionar Profesores</h5>
                        <p class="card-text">Edita el personal, puedes actualizar su estado.</p>
                    </div>
                </div>
            </a>
        </div>
        <!-- Tarjeta Gestionar Clases -->
        <div class="col-md-3 col-sm-6 text-center mb-4">
            <a href="" class="button">
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
        <div class="col-md-3 col-sm-6 text-center mb-4">
            <a href="" class="button">
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
        <div class="col-md-3 col-sm-6 text-center mb-4">
            <a href="" class="button">
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
        <div class="col-md-3 col-sm-6 text-center mb-4">
            <a href="" class="button">
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
        <div class="col-md-3 col-sm-6 text-center mb-4">
            <a href="" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/asignacionClases.png" class="text-center" width="68">
                        <h5 class="card-title">Asignación de clase</h5>
                        <p class="card-text">Si ya tienes la encuesta, crea una nueva clase.</p>
                    </div>
                </div>
            </a>
        </div>
        <!-- Tarjeta Invisible -->
        <div class="col-md-3 col-sm-6 text-center mb-4 invisible-card">
            <a href="" class="button">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Invisible Card</h5>
                        <p class="card-text">This card is invisible.</p>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>
</body>
</html>
