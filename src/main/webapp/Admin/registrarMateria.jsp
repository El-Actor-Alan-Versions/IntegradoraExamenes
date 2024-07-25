<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Materia</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<div class="container mt-4">
    <h2>Registrar Nueva Materia</h2>
    <form action="registrarMateriaServlet" method="post">
        <div class="form-group">
            <label for="nombreMateria">Nombre de la Materia:</label>
            <input type="text" class="form-control" id="nombreMateria" name="nombreMateria" required>
        </div>
        <button type="submit" class="btn btn-primary">Registrar Materia</button>
    </form>
</div>
<script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath}/JS/bootstrap.js"></script>
</body>
</html>
