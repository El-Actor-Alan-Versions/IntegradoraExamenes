<%--
  Created by IntelliJ IDEA.
  User: walge
  Date: 23/07/2024
  Time: 09:15 p. m.
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .navbar-custom {
            background-color: #85C5B7;
            height: 120px;
            width: 100%;
            max-width: 1440px;
            margin: auto;
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
            color: black;
        }

        .unu {
            color: black;
            text-decoration: none;
            font-family:'PT Sans';
            font-weight: bold;
            margin: 0%;
        }

        .button {
            position: relative;
            width: 150px; /* Ajusta el ancho según sea necesario */
            text-align: center;
        }



        .unu {
            position: relative;
            top: 95%;
            left: 45%;
            transform: translate(-50%, -50%);
            margin: 0;
            padding: 5px;
            border-radius: 0; /* Sin borde redondeado */
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
            border-radius: 0; /* Sin borde redondeado */
        }


    </style>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-custom">
    <div class="profile-button">
        <img src="../img/Logo-utez .png" id="logo" alt="Logo">
    </div>
    <div class="d-flex flex-grow-1 justify-content">
        <p class="navbar-text">PLATAFORMA DE EXÁMENES</p>
    </div>
    <a href="" class="button">
        <img src="../img/miPerfil.png" alt="perfil" hrf="">
        <p class="unu">Mi Perfil</p>
    </a>
</nav>

</body>
</html>