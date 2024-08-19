<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');

        .navbar-custom {
            background-color: #85C5B7;
            height: 180px;
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .navbar-text {
            font-family: 'PT Sans';
            font-weight: bold;
            font-size: 36px;
            letter-spacing: 0.29em;
            color: rgb(41, 39, 39);
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }

        a {
            color: rgb(41, 39, 39);
            font-family:'PT Sans';
            font-weight: bold;
            margin: 0;
            text-decoration: none;
        }

        .custom-button {
            position: relative;
            width: 20px; /* Ajusta el tamaño aquí */
            margin-bottom: 12px; /* Ajusta la posición vertical si es necesario */
            text-align: center;
        }

        .custom-button img {
            width: 60px; /* Ajusta el tamaño de la imagen aquí */
            height: auto;
        }

        #logo {
            width: 250px;
            height: auto;
            padding: 5%;
        }

        .profile-button img {
            width: 317px;
            height: 160px;
            max-width: 100%;
            height: auto;
        }

        @media (max-width: 768px) {
            .navbar-custom {
                flex-direction: column;
                height: auto;
                text-align: center;
            }

            .navbar-text {
                font-size: 28px;
                margin: 10px 0;
            }

            .profile-button img {
                width: 200px;
                height: auto;
            }

            #logo {
                width: 200px;
                padding: 2%;
            }
        }

        @media (max-width: 480px) {
            .navbar-text {
                font-size: 24px;
            }

            .profile-button img {
                width: 20px;
                height: auto;
            }

            #logo {
                width: 150px;
                padding: 2%;
            }
        }

        .dropdown {
            position: relative;
            margin-right: 30px;
        }

        .dropdown-menu {
            background-color: white; /* Mantener el fondo del dropdown transparente */
            border: none; /* Sin borde en el contenedor del menú */
            max-width: 100%;
            width: 220px;
            margin-left: -50px; /* Ajuste para que quede más alineado a la derecha */
        }

        .dropdown-item {
            background-color: white; /* Fondo blanco para los elementos */
            border: none; /* Borde negro de 2px */
            color: #292727; /* Color negro para el texto */
            font-family: 'PT Sans'; /* Fuente utilizada */
            font-size: 18px;
            text-align: center; /* Alineación del texto a la derecha */
        }

        .dropdown-item:hover {
            background-color: rgba(133, 197, 183, 0.3); /* Fondo de hover translúcido */
            color: #000; /* Color del texto en hover */
        }

        .dropdown-toggle {
            background-color: transparent;
            border: none;
            color: #292727;
            font-family: 'PT Sans';
            font-weight: bold;
            font-size: 18px;
            letter-spacing: 0.1em;
            text-transform: uppercase;
        }



    </style>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="profile-button">
        <img src="../img/Logo-utez.png" id="logo" alt="Logo UTEZ">
    </a>
    <div class="d-flex flex-grow-1 justify-content-center">
        <p class="navbar-text">PLATAFORMA DE EXÁMENES</p>
    </div>

    <div class="dropdown" title="Inicio" >
        <button class="btn-secondary dropdown-toggle" title="Inicio" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Menú
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="indexAdmin.jsp">Inicio</a>
            <a class="dropdown-item" href="cambiarContra.jsp">Contraseña</a>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Cerrar Sesión</a>
        </div>
    </div>
</nav>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
