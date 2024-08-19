<%--
  Created by IntelliJ IDEA.
  User: coke_
  Date: 24/07/2024
  Time: 12:14 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <title>Alumno</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/usuarios.css">


</head>
<!-- Incluir el JS de Bootstrap y dependencias -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<body>
<div id="navbar"></div>
<script>
    fetch('navbar.jsp')
        .then(response => response.text())
        .then(data => {
            document.getElementById('navbar').innerHTML = data;
            $('.dropdown-toggle').dropdown();  // Inicializar el dropdown manualmente
        });

</script>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-4 col-sm-6 text-center mb-4">
            <!-- aqui va la direccion de la pagina -->
            <a href="VerExamenesAlumnoServlet" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/examenesDisponibles.png" class="text-center" width="68">
                        <h5 class="card-title">Examenes Disponibles</h5>
                        <p class="card-text">Revisa los examenes disponibles.</p>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-md-4 col-sm-6 text-center mb-4">
            <!-- aqui va la direccion de la pagina -->
            <a href="verCalificaciones.jsp" class="button">
                <div class="card">
                    <div class="card-body">
                        <img src="../img/mirarCalificaciones.png" class="text-center" width="68">
                        <h5 class="card-title">Mirar Calificaciones</h5>
                        <p class="card-text">Mira tus calificaciones de exámenes ya contestados.</p>
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
