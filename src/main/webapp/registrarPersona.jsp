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
        <img src="img/iconoRobado.png" width="48" alt="icono">
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
    <form id="registroPersona" method="POST" action="registrarUsuario">
        <div class="mb-3">
            <label for="matricula" class="form-label">Matrícula</label>
            <input type="text" class="form-control" id="matricula" name="matricula" required>
        </div>
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>
        <div class="mb-3">
            <label for="apellidoPaterno" class="form-label">Apellido Paterno</label>
            <input type="text" class="form-control" id="apellidoPaterno" name="apellidoPaterno" required>
        </div>
        <div class="mb-3">
            <label for="apellidoMaterno" class="form-label">Apellido Materno</label>
            <input type="text" class="form-control" id="apellidoMaterno" name="apellidoMaterno" required>
        </div>
        <div class="mb-3">
            <label for="correo" class="form-label">Correo Electrónico</label>
            <input type="email" class="form-control" id="correo" name="correo" required>
        </div>
        <div class="mb-3">
            <label for="contrasena" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="contrasena" name="contrasena" required>
        </div>
        <div class="mb-3">
            <label for="tipoUsuario" class="form-label">Tipo de Usuario</label>
            <select class="form-control" id="tipoUsuario" name="tipoUsuario" required>
                <option value="" disabled selected>Selecciona el tipo de usuario</option>
                <option value="administrador">Administrador</option>
                <option value="docente">Docente</option>
                <option value="alumno">Alumno</option>
                <!-- Agrega más opciones según tus necesidades -->
            </select>
        </div>
        <div class="mb-3" id="grupo-container" style="display: none;">
            <label for="id_grupo" class="form-label">ID del Grupo</label>
            <input type="text" class="form-control" id="id_grupo" name="id_grupo">
        </div>
        <button type="submit" class="btn btn-primary m-2">Registrar</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.getElementById('tipoUsuario').addEventListener('change', function () {
        const grupoContainer = document.getElementById('grupo-container');
        if (this.value === 'alumno') {
            grupoContainer.style.display = 'block';
        } else {
            grupoContainer.style.display = 'none';
        }
    });
</script>
</body>
</html>
