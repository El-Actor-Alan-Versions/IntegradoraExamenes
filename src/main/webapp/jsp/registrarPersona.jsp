<%--
  Created by IntelliJ IDEA.
  User: walge
  Date: 23/06/2024
  Time: 06:44 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Persona</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <img src="../img/iconoRobado.png" width="48" alt="icono">
        <a class="navbar-brand">Formularios</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="../indexAdmin.html">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="html/registrarPersona.html">Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="html/gestionarGrupos.html">Grupos</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="text-center">Registrar Persona</h2>
    <form id="registroPersona" method="POST" action="sign_in">
        <div class="mb-3">
            <label for="nombre_usuario" class="form-label">Nombre de Usuario</label>
            <input type="text" class="form-control" id="nombre_usuario" name="nombre_usuario" required>
        </div>
        <div class="mb-3">
            <label for="pass1" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="pass1" name="pass1" required>
        </div>
        <div class="mb-3">
            <label for="pass2" class="form-label">Repite Contraseña</label>
            <input type="password" class="form-control" id="pass2" name="pass2" required>
        </div>
        <div class="mb-3">
            <label for="correo_usuario" class="form-label">Correo Electrónico</label>
            <input type="email" class="form-control" id="correo_usuario" name="correo_usuario" required>
        </div>
        <div class="mb-3">
            <label for="tipo_usuario" class="form-label">Tipo de Usuario</label>
            <select class="form-control" id="tipo_usuario" name="tipo_usuario" required>
                <option value="" disabled selected>Selecciona el tipo de usuario</option>
                <option value="1">Administrador</option>
                <option value="2">Docente</option>
                <!-- Agrega más opciones según tus necesidades -->
            </select>
        </div>
        <button type="submit" class="btn btn-primary m-2">Registrar</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/registroPersona.js"></script>
</body>
</html>


