<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Éxito</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');

        .container {
            margin-top: 40px;
            background-color: #B8DDC0;
            padding: 20px;
            border-radius: 8px;
            font-family: 'PT Sans', sans-serif;
            max-width: 90%;
            height: 100%;
            width: 100%;
            text-align: center;
        }

        .alert-custom {
            background-color: #9EEB92;
            color: black;
            font-size: 1.5rem;
            padding: 30px;
            border-radius: 8px;
            position: relative;
        }

        .alert-custom p {
            margin: 0;
        }

        .btn-custom {
            background-color: #FFFFFF;
            border: 2px solid #9EEB92;
            border-radius: 22px;
            font-size: 1.2rem;
            margin-top: 20px;
            padding: 10px 30px;
            text-decoration: none;
            color: #9EEB92;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .btn-custom:hover {
            background-color: #88d67c;
            color: white;
            transform: scale(1.05);
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <div class="alert alert-custom" role="alert">
        <p>El examen ha sido creado exitosamente.</p>
        <a href="indexDocente.jsp" class="btn btn-custom">Volver</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
