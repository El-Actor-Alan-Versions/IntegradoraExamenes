<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recuperar Contraseña</title>
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
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="text-center mt-5">Solicitud de Recuperación de contraseña</h2>
            <form method="post" action="recuContra" class="mt-4">
                <div class="form-group mb-3">
                    <label for="correo">Ingrese su correo:</label>
                    <input type="email" class="form-control" id="correo" name="correo" required>
                </div>
                <%
                    HttpSession sesion = request.getSession();
                    String mensaje = (String) sesion.getAttribute("mensaje");
                    sesion.setAttribute("mensaje", null);

                    if(mensaje != null){ %>
                <p class="text-danger"><%=mensaje%></p>
                <% } %>
                <div class="text-center">
                    <button type="submit" class="btn btn-dark botonesApp btn-block">Solicitar</button>
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