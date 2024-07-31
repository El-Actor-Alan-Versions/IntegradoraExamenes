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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <title>Docente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/usuarios.css">
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
        <div class="col-md-4 col-sm-6 text-center mb-4">
            <!-- aqui va la direccion de la pagina -->
            <a href="crearExamen.jsp" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/añadirExamen.png" class="text-center" width="68">
                        <h5 class="card-title">Crear Examen</h5>
                        <p class="card-text">Diseña y publica nuevos exámenes fácilmente.</p>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-md-4 col-sm-6 text-center mb-4">
            <!-- aqui va la direccion de la pagina -->
            <a href="VerExamenesServlet" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="img/examenesActivos.png" class="text-center" width="68">
                        <h5 class="card-title">Examenes Activos</h5>
                        <p class="card-text">Observa que exámenes tienes activos</p>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-md-4 col-sm-6 text-center mb-4">
            <!-- aqui va la direccion de la pagina -->
            <a href="editarPerfil.jsp" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="img/calificaciones 1.png" class="text-center" width="68">
                        <h5 class="card-title">Gestionar Calificaciones</h5>
                        <p class="card-text">Administra y revisa las calificaciones de los estudiantes.</p>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>

<footer>

</footer>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
