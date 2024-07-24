<%--
  Created by IntelliJ IDEA.
  User: coke_
  Date: 24/07/2024
  Time: 01:21 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<style>
    @import url('https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');

    .container{
        margin-top: 40px;
        background-color: #B8DDC0;
        padding: 20px;
        border-radius: 8px;
        font-family: 'PT Sans';
        font-size: 48px;
        font-weight: Regular;
        color: black;
        max-width: 90%;
        width: 1367px;
        height: 560px;
    }

    .card {
        background-color: #FFFFFF;
        padding: 15px;
        border-radius: 8px;
        margin-bottom: 20px;
        align-items: center;
        max-width: 100%;
        width: 1340px;
        height: 520px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center ;
    }

    .btn-custom {
        background-color: #9EEB92;
        border-radius: 22px;
        font-size: 24px;
        margin-top: 40px;
        height: 32.83px;
        width: 185.2px;
        display: flex;
        justify-content: center;
        align-items: center;
        text-decoration: none;
        transition: background-color 0.3s ease, transform 0.3s ease;
    }

    .btn-custom:hover {
        background-color: #88d67c;
        transform: scale(1.05);
    }



    footer {
        margin-top: 15px;
    }
</style>
</body>

<div id="navbar"></div>
<script>
    fetch('navbar.jsp')
        .then(response => response.text())
        .then(data => {
            document.getElementById('navbar').innerHTML = data;
        });
</script>
<div class="container">
    <div class="card">
        <p class="text-center">¡Felicidades, vas al dia con tus examenes!</p>
        <img src="../img/line.png">

        <a href="alumno.html" class="btn btn-custom">Volver</a>
    </div>
</div>

<footer>

</footer>
</html>
