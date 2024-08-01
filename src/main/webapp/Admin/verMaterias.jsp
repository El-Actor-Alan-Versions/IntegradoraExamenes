<%@ page import="mx.edu.utez.integradiratjuans.dao.MateriaDao" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Materia" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Materias Registradas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables.css">
    <style>
        /* usuarios.css */

        @import url('https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');

        body {
            font-family: 'PT Sans';
            color: rgb(17, 16, 16);
            justify-content: center;
            align-items: center;
            background-color: #EEEEEE;
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
        }

        .container {
            background: #b8eac2;
            font-family: 'PT Sans', sans-serif;
            width: 100%;
            align-items: center;
            margin-top: 20px; /* Margen superior añadido */
        }

        .card{
            width: 100%;
            height: 100%;
            background: none;
            border: none;
            align-items: center;
            margin-top: 10px;
        }

        .card-body{
            background: white;
            width: 80%;
            overflow-x: auto;
            border-radius: 20px;
            margin-top: 20px;
        }

        .table-custom {
            padding: 30px 13%;
            text-align: center;
            background: white;
            border-radius: 15px;
            margin: 20px auto;
            width: 90%;
            height: auto;
            background: white;
            border-collapse: collapse
        }

        .table-custom th{
            border: 2px solid black; /* Ajusta el grosor y el color del borde */
            padding: 10px; /* Ajusta el relleno interno de las celdas */
        }

        .table-custom td {
            border: 2px solid black; /* Ajusta el grosor y el color del borde */
            padding: 10px; /* Ajusta el relleno interno de las celdas */
        }

        .header-card {
            background-color: #CDFFF4;
            padding: 15px;
            border-radius: 8px;
            display: flex;
            align-items: center;
            justify-content: center; /* Centrar horizontalmente */
            width: 90%;
            margin-top: 10px;
            margin-left: 5%
            gap: 15px;
        }


        .header-card img {
            margin-left: 15px;
        }

        .header-card h2 {
            flex: 1;
            text-align: center;
        }

        .btn-new {
            background: #FED8D8;
            font-weight: 800;
            width: 150px;
            border-radius: 18px;
            margin-right: 10px;
        }

        .btn-conf {
            background: #9EEB92;
            font-weight: 800;
            width: 150px;
            border-radius: 18px;
        }
        .btn-act{
            background: #FED8D8;
            font-weight: 700;
            width: 80%;
            border-radius: 22px;
        }
        .btn-del{
            background: #DD5151;
            font-weight: 800;
            width: 80%;
            border-radius: 22px;
        }
    </style>
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

<div class="container">
    <div class="card">
        <div class="header-card">
            <img src="../img/calificaciones.png" width="68">
            <h2>Gestión de materias</h2>
            <button type="button" class="btn btn-new" onclick="location.href='registrarMateria.jsp'">Nueva Materia</button>
            <button type="button" class="btn btn-conf" onclick="location.href='indexAdmin.jsp'">Confirmar</button>
        </div>
        <div class="card-body">
            <table id="example" class="table table-custom">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre de Materia</th>
                    <th>Actualizar</th>
                    <th>Eliminar</th>
                </tr>
                </thead>
                <tbody>
                <%
                    try {
                        MateriaDao dao = new MateriaDao();
                        List<Materia> lista = dao.getAll();
                        for (Materia m : lista) {
                %>
                <tr>
                    <td><%= m.getIdMateria() %></td>
                    <td><%= m.getNombreMateria() %></td>
                    <td>
                        <a href="registrarMateria.jsp?idMateria=<%=m.getIdMateria() %>" class="btn btn-act">Modificar</a>
                    </td>
                    <td>
                        <form method="post" action="eliminarMateriaServlet">
                            <input type="hidden" name="idMateria" value="<%= m.getIdMateria() %>">
                            <button type="submit" class="btn btn-del" onclick="return confirmarEliminacion()">Eliminar</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        e.printStackTrace();
                    }
                %>
                </tbody>
            </table>

        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath}/JS/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/JS/datatables.js"></script>
<script src="${pageContext.request.contextPath}/JS/dataTables.bootstrap5.js"></script>
<script src="${pageContext.request.contextPath}/JS/es-MX.json"></script>
<script>
    document.addEventListener('DOMContentLoaded', () => {
        const table = document.getElementById('example');
        new DataTable(table, {
            language: {
                url: '${pageContext.request.contextPath}/JS/es-MX.json'
            }
        });
    });

    function confirmarEliminacion() {
        return confirm('¿Estás seguro de que deseas eliminar esta materia?');
    }

    function confirmarCambios() {
        alert('Cambios confirmados');
    }
</script>
</body>
</html>
