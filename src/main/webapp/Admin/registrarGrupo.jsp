<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <title>Registrar Grupo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/usuarios.css">
</head>
<body>
<div id="navbar"></div>
<script>
    fetch('navbar.jsp')
        .then(response => response.text())
        .then(data => {
            document.getElementById('navbar').innerHTML = data;
        });
</script>

<div class="container mt-4">
    <h1 class="mb-4">Registrar Nuevo Grupo</h1>
    <form action="registrarGrupoServlet" method="post">
        <div class="form-group">
            <label for="gradoGrupo">Grado del Grupo:</label>
            <input type="text" class="form-control" id="gradoGrupo" name="gradoGrupo" required />
        </div>

        <div class="form-group">
            <label for="idCarrera">Carrera:</label>
            <select id="carrerasSelect" class="form-control" name="idCarrera" required>
                <option value="">Seleccione una carrera...</option>
            </select>
        </div>

        <button type="submit" class="mt-4 btn btn-primary">Registrar Grupo</button>
    </form>
</div>

<!-- Incluir el JS de Bootstrap y dependencias -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        fetch('${pageContext.request.contextPath}/GetCarrerasServlet')
            .then(response => response.json())
            .then(data => {
                const selectElement = document.getElementById('carrerasSelect');
                data.forEach(carrera => {
                    const option = document.createElement('option');
                    option.value = carrera.id_carrera;
                    option.text = carrera.nombre_carrera;
                    selectElement.appendChild(option);
                });
            })
            .catch(error => console.error('Error:', error));
    });
</script>
</body>
</html>
