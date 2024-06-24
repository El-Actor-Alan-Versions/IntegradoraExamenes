<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Plataforma de exámenes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-utilities.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="form-center">
    <form id="formularioInsano" class="form-container" method="post" action="login">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-12">
                    <div class="form-control-sm col-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-control text-center">
                            <div class="col-12 text-center">
                                <img class="text-center" src="img/usuario.png" width="68" alt="perfil">
                                <br>
                            </div>
                            <br>
                            <h1 class="text-center">Inicio de sesión</h1>
                            <br>
                            <%-- Mostrar mensaje de error si está presente en la sesión --%>
                            <% HttpSession sesion = request.getSession();
                                String mensaje = (String) sesion.getAttribute("mensaje");
                                if (mensaje != null) { %>
                            <div class="alert alert-danger d-flex justify-content-center">
                                <%= mensaje %>
                            </div>
                            <% } %>
                            <div class="form-control-sm">
                                <div class="form-group form-control-sm">
                                    <input class="form-control rounded-pill text-muted" id="nombre_usuario" name="nombre_usuario" placeholder="Nombre de usuario" type="text" required>
                                </div>
                            </div>
                            <div class="form-control-sm">
                                <div class="form-group form-control-sm">
                                    <input class="form-control rounded-pill text-muted" id="contra" name="contra" placeholder="Contraseña" type="password" required>
                                </div>
                            </div>
                            <div class="form-control-sm">
                                <div class="link text-end">
                                    <a class="text-black" href="">¿Olvidaste contraseña?</a>
                                </div>
                            </div>
                            <br>
                            <div class="d-flex justify-content-center">
                                <button type="submit" class="btn btn-block rounded-pill">Iniciar Sesión</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
