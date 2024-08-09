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

        .button {
            position: relative;
            width: 150px;
            text-align: center;
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

            .button {
                width: 100%;
                margin-top: 10px;
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
                width: 150px;
                height: auto;
            }

            #logo {
                width: 150px;
                padding: 2%;
            }
        }

        .dropdown{
            background-color: transparent;
            background: transparent;
            border: transparent;
        }
    </style>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-custom">
    <a href="indexAdmin.jsp" target="_blank" class="profile-button">
        <img src="../img/Logo-utez.png" id="logo" alt="Logo UTEZ">
    </a>
    <div class="d-flex flex-grow-1 justify-content">
        <p class="navbar-text">PLATAFORMA DE EXÁMENES</p>
    </div>
    <a href="" class="button">
        <img src="../img/miPerfil.png" alt="perfil">
    </a>

    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Mi perfil
        </button>
        <div class=" dropdown-menu" aria-labelledby="dropdownMenuButton">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Cerrar Sesión</a></li>
            <a class="dropdown-item" href="#">(PENDIENTE)</a>
        </div>
    </div>
</nav>
</body>
</html>