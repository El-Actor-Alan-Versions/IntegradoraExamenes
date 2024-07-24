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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/docente.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-custom">
    <div class="profile-button">
        <img src="../img/Logo-utez%20.png" id="logo" alt="Logo">
    </div>
    <div class="d-flex flex-grow-1 justify-content">
        <p class="navbar-text">PLATAFORMA DE EXÁMENES</p>
    </div>
    <img src="../img/miPerfil.png" alt="perfil">
</nav>

<section class="options-section py-5">
    <div class="container">
        <!-- Contenedor más grande -->
        <div class="card">
            <div class="card-body +">
                <div class="row">
                    <div class="col-md-4 text-center">
                        <div class="card">
                            <div class="card-body">
                                <img src="../img/añadir%20(2).png" class="text-center" width="68" alt="Examen">
                                <h5 class="card-title">Gestionar Alumnos</h5>
                                <p class="card-text">Gestiona al Alumnado, puedes actualizar el estado de los activos e inactivos.</p>
                                <a href="verAlumnos.jsp" class="btn btn-primary rounded-pill">Crear</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 text-center">
                        <div class="card">
                            <div class="card-body">
                                <img src="../img/calificaciones.png" class="text-center" width="68" alt="calificaciones">
                                <h5 class="card-title">Examenes Activos</h5>
                                <p class="card-text">Wacha los examenes.</p>
                                <a href="VerExamenes.jsp" class="btn btn-primary rounded-pill">Gestionar</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 text-center">
                        <div class="card">
                            <div class="card-body">
                                <img src="../img/perfil%20(2).png" class="text-center" width="68" alt="usuario">
                                <h5 class="card-title">Modificar Perfil</h5>
                                <p class="card-text">Actualiza tu información personal y ajustes de cuenta.</p>
                                <a href="editarPerfil.jsp" class="btn btn-primary rounded-pill">Modificar</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 text-center">
                        <div class="card">
                            <div class="card-body">
                                <img src="../img/perfil%20(2).png" class="text-center" width="68" alt="usuario">
                                <h5 class="card-title">Modificar Perfil</h5>
                                <p class="card-text">Actualiza tu información personal y ajustes de cuenta.</p>
                                <a href="editarPerfil.jsp" class="btn btn-primary rounded-pill">Modificar</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 text-center">
                        <div class="card">
                            <div class="card-body">
                                <img src="../img/perfil%20(2).png" class="text-center" width="68" alt="usuario">
                                <h5 class="card-title">Modificar Perfil</h5>
                                <p class="card-text">Actualiza tu información personal y ajustes de cuenta.</p>
                                <a href="editarPerfil.jsp" class="btn btn-primary rounded-pill">Modificar</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 text-center">
                        <div class="card">
                            <div class="card-body">
                                <img src="../img/perfil%20(2).png" class="text-center" width="68" alt="usuario">
                                <h5 class="card-title">Modificar Perfil</h5>
                                <p class="card-text">Actualiza tu información personal y ajustes de cuenta.</p>
                                <a href="editarPerfil.jsp" class="btn btn-primary rounded-pill">Modificar</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 text-center">
                        <div class="card">
                            <div class="card-body">
                                <img src="../img/perfil%20(2).png" class="text-center" width="68" alt="usuario">
                                <h5 class="card-title">Modificar Perfil</h5>
                                <p class="card-text">Actualiza tu información personal y ajustes de cuenta.</p>
                                <a href="editarPerfil.jsp" class="btn btn-primary rounded-pill">Modificar</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 text-center">
                        <div class="card">
                            <div class="card-body">
                                <img src="../img/perfil%20(2).png" class="text-center" width="68" alt="usuario">
                                <h5 class="card-title">Modificar Perfil</h5>
                                <p class="card-text">Actualiza tu información personal y ajustes de cuenta.</p>
                                <a href="editarPerfil.jsp" class="btn btn-primary rounded-pill">Modificar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fin del contenedor más grande -->
    </div>
</section>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>
</html>