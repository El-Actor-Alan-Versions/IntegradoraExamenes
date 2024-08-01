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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
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
            <img src="IMG2/login.png" alt="profile icon" class="profile-icon">
            <p>INICIO DE SESIÓN</p>
            <form   id="formularioInsano" class="form-control border-0 text-center"  action="login" method="post">
                <%-- Mostrar mensaje de error si está presente en la sesión --%>
                <% HttpSession sesion = request.getSession();
                    String mensaje = (String) sesion.getAttribute("mensaje");
                    if (mensaje != null) { %>
                <div class="alert alert-danger d-flex justify-content-center">
                    <%= mensaje %>
                </div>
                <% } %>
                <div class="form-group mb-3">
                    <input type="text" required class="form-control rounded-pill text-center" name="matricula" id="matricula" placeholder="MATRICULA">
                </div>
                <div class="form-group mb-3">
                    <input type="password" required class="form-control rounded-pill text-center" name="contra" id="contra" placeholder="CONTRASEÑA">
                </div>
                <div class="container si justify-content-between">
                    <a href="#" class="forgot-password ">¿Has olvidado tu contraseña?</a>
                </div>

                <div class="form-floating mt-3">
                    <button type="submit" class="btn rounded-pill" id="loginButton"> INICIAR SESIÓN</button>
                </div>
            </form>
        </div>
    </div>
</div>

<img src="IMG2/Rectangle 27.png" alt="rectangulo" class="rectangulo2">
<img src="IMG2/Rectangle 29.png" alt="rectangulo" class="rectangulo3">

<footer class="container">
</footer>
<script>
    document.getElementById('loginButton').addEventListener('submit', function(event) {
        event.preventDefault();
        const matricula = document.getElementById('matricula').value;
        const contra = document.getElementById('contra').value;
        if (matricula === 'matricula' && contra === 'contra') {
            // Inicio de sesión exitoso
            Swal.fire({
                html: '<b class= "titulo">¡Inicio de sesión exitoso!</b>',
                icon: 'success',
                width: '40%',
                padding: '4rem',
                customClass:
                    {
                        popup: 'popup-class'
                    }
            });
        } else {
            // Inicio de sesión fallido
            Swal.fire({
                html: '<b class= "titulo">¡Inicio de sesión fallido!</b>',
                icon: 'error',
                width: '40%',
                padding: '4rem',
                customClass:
                    {
                        popup: 'popup-class'
                    }
            });
</script>
<script src="js/sweetAlert.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>