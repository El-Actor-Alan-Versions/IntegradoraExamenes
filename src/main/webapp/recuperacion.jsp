<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Plataforma de exámenes</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" >
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-utilities.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/recuperacion.css">
</head>
<body>

<header class="py-3 position-relative">
    <div class="container d-flex justify-content-between align-items-center">
        <div class="col-custom text-end">
            <p class="m-0">PLATAFORMA DE EXÁMENES</p>
        </div>
        <div class="col text-end">
            <img src="IMG2/Logo-utez .png" width="180px" height="auto" alt="logo">
        </div>
    </div>
</header>
<img src="IMG2/Rectangle 30.png" alt="rectangulo" class="rectangulo">
<div class="container">
    <div class="form text-center d-flex justify-content-center align-items-center vh-100">
        <div class="text-center">
            <img src="img/contrasena.png" alt="profile icon" class="profile-icon">
            <p>SOLICITUD PARA CAMBIAR CONTRASEÑA</p>
            <form method="post" action="recuContra" >
                <div class="form-group mb-3">
                    <input type="email" required class="form-control rounded-pill text-center" name="correo" id="correo"   placeholder="INGRESE SU CORREO" >
                </div>
                <%
                    HttpSession sesion = request.getSession();
                    String mensaje = (String) sesion.getAttribute("mensaje");
                    sesion.setAttribute("mensaje", null);

                    if(mensaje != null){ %>
                <p class="text-danger"><%=mensaje%></p>
                <% } %>
                <div class="form-floating mt-3">
                    <button type="submit" class="btn rounded-pill" id="Solicitar" value="Solicitar"> SOLICITAR</button>
                </div>
            </form>
        </div>
    </div>
</div>

<img src="IMG2/Rectangle 27.png" alt="rectangulo" class="rectangulo2">
<img src="IMG2/Rectangle 29.png" alt="rectangulo" class="rectangulo3">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>