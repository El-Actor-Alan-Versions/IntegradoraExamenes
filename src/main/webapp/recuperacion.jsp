<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recuperar Contrasena</title>
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
        <p>RECUPERACIÓN DE CONTRASEÑA</p>
    <form id="forgot-password-form">
        <div class="form-group mb-3">
            <label for="email">Correo Electronico</label>
            <input type="email" required class="form-control rounded-pill text-center" name="email" id="email" placeholder="EMAIL" >
        </div>
        <div class="form-floating mt-3">
            <button type="submit" class="btn rounded-pill" id="enviar"> ENVIAR</button>
        </div>
    </form>
</div>
</div>

<img src="IMG2/Rectangle 27.png" alt="rectangulo" class="rectangulo2">
<img src="IMG2/Rectangle 29.png" alt="rectangulo" class="rectangulo3">

<script>
    document.getElementById('forgot-password-form').addEventListener('submit', function (event) {
        event.preventDefault();
        const email = document.getElementById('email').value;
        fetch('/send-reset-password-email', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({email})
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Se ha enviado un correo para restablecer la contraseña.');
                } else {
                    alert('Hubo un error al enviar el correo.');
                }
            });
    });
    <footer className="container">
    </footer>
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
