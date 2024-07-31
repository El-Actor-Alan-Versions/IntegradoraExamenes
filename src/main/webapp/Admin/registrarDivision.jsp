<%@ page import="mx.edu.utez.integradiratjuans.dao.DivisionDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Division" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Division division = null;
    String action = "insert";
    int idDivision = 0;

    // Si se pasa un idDivision en la URL, cargamos los datos para actualización
    String idDivisionParam = request.getParameter("idDivision");
    if (idDivisionParam != null && !idDivisionParam.isEmpty()) {
        idDivision = Integer.parseInt(idDivisionParam);
        action = "update";

        DivisionDao dao = new DivisionDao();
        try {
            division = dao.getById(idDivision);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= action.equals("update") ? "Actualizar" : "Registrar" %> División</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <style>
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

        .card {
            background: #B8DDC0;
            height: 80%;
        }

        .header-card {
            margin-outside: 20px;
            background-color: #CDFFF4;
            padding: 15px;
            border-radius: 8px;
            display: flex;
            align-items: center;
            width: 80%;
            margin-bottom: 10px;
            margin-left: 10%;
            margin-top: 10px;
        }

        .card-body {
            padding: 30px 13%;
            text-align: center;
            background: white;
            border-bottom: 20px;
            width: 100%;
            max-width: 700px;
            border-radius: 15px;
            margin-bottom: 20px;
            margin-left: 18%;
        }

        .label-input-group {
            display: flex;
            align-items: center;
            margin-right:20px;
            margin-bottom: 15px;
            width: 100%;
            max-width: 600px;
        }



        h1, h2 {
            text-align: center;
            width: 100%;
        }

        label {
            font-family: 'PT Sans';
            font-weight: bold;
            border-radius: 25px;
            width: 230px;
            text-align: right;
            margin-right: 10px;
        }

        input.form-control, select.form-control {
            font-weight: 600;
            border-radius: 25px;
            box-sizing: border-box;
            width: 90%;
            background-color: #85C5B7;
            text-align: center;
        }

        .btn {
            background-color: #FF9191;
            border-radius: 50px;
            width: 170px;
            color: black;
            padding: 5px;
            margin-top: 20px;
            font-weight: 700;
            display: inline-block;
            margin-bottom: 20px;
        }

        .btn-right {
            margin-left: auto;
            margin-right: 100px;
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

<div class="container mt-4">
    <div class="card">
        <div class="header-card">
            <img src="../img/crear.png" width="68px">
            <h2 class="text-center"><%= action.equals("update") ? "Actualizar" : "Registrar" %> División</h2>
        </div>
        <div class="card-body">
            <form action="<%= action.equals("update") ? "actualizarDivisionServlet" : "registrarDivisionServlet" %>" method="post">
                <% if (action.equals("update")) { %>
                <input type="hidden" name="idDivision" value="<%= division.getIdDivision() %>">
                <% } %>
                <div class="label-input-group">
                    <label for="nombreDivision" class="form-label">Nombre de la División</label>
                    <input type="text" class="form-control" id="nombreDivision" name="nombreDivision" value="<%= action.equals("update") ? division.getNombreDivision() : "" %>" required>
                </div>
                <button type="submit" class="btn btn-right"><%= action.equals("update") ? "Actualizar" : "Registrar" %></button>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath}/JS/bootstrap.js"></script>
</body>
</html>
