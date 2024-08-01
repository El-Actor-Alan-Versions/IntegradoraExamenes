<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mx.edu.utez.integradiratjuans.model.Grupo" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.GrupoDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Carrera" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.CarreraDao" %>
<%@ page import="java.util.List" %>
<%
    Grupo grupo = null;
    String action = "insert";
    int  idGrupo = 0;

    String idCarreraParam = request.getParameter("idGrupo");
    if (idCarreraParam != null && !idCarreraParam.isEmpty()) {
        idGrupo = Integer.parseInt(idCarreraParam);
        action = "update";

        GrupoDao dao = new GrupoDao();
        try{
            grupo = dao.getById(idGrupo);
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <title><%= action.equals("update") ? "Actualizar" : "Registrar" %> Grupo</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');

        body {
            font-family: 'PT Sans';
            color: rgb(17, 16, 16);
            justify-content: center;
            align-items: center;
            background-color: #EEEEEE;
            width: 100%;
            margin: 0;
            padding: 0;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
        }

        .card {
            background: #B8DDC0;
            max-width: 1367px;
            margin: 0 auto;
        }

        .header-card {
            background-color: #CDFFF4;
            padding: 15px;
            border-radius: 8px;
            display: flex;
            align-items: center;
            width: 80%;
            margin: 10px auto;
        }

        .card-body {
            padding: 30px 13%;
            text-align: center;
            background: white;
            border-radius: 15px;
            margin: 20px auto;
            width: 70%;
            box-sizing: border-box;
        }

        .label-input-group {
            display: flex;
            align-items: center;
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
            display: block;
            margin: 20px auto;
        }

        @media (max-width: 768px) {
            .header-card, .card-body {
                width: 95%;
                padding: 20px 5%;
                margin-left: auto;
                margin-right: auto;
            }

            .header-card {
                flex-direction: column;
                align-items: flex-start;
                text-align: center;
            }

            .label-input-group {
                flex-direction: column;
                align-items: flex-start;
                width: 100%;
            }

            label {
                width: 100%;
                text-align: left;
                margin-bottom: 5px;
            }

            input.form-control, select.form-control {
                width: 100%;
            }
        }
    </style></head>
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
            <h2 class="text-center"><%=  action.equals("update") ? "Actualizar" : "Registrar"%> Grupo</h2>
        </div>
        <div class="card-body">
            <form action="<%= action.equals("update") ? "actualizarGrupoServlet" : "registrarGrupoServlet"%>" method="post">
                <% if (action.equals("update")){ %>
                <input type="hidden" name="idGrupo" value="<%= grupo.getIdGrupo()%>">
                <% } %>
                <div class="label-input-group">
                    <label for="gradoGrupo" class="form-label">Grado del Grupo:</label>
                    <input type="text" class="form-control" id="gradoGrupo" name="gradoGrupo"  value="<%= action.equals("update") ? grupo.getGradoGrupo() : ""%>" required />
                </div>
                <div class="label-input-group">
                    <label for="idCarrera" class="form-label">Carrera:</label>
                    <select id="idCarrera" class="form-control" name="idCarrera" required>
                        <option value="">Seleccione...</option>
                        <%
                            CarreraDao carreraDao = new CarreraDao();
                            List<Carrera> carreras = carreraDao.getAll();
                            for (Carrera carrera : carreras) {
                        %>
                        <option value="<%= carrera.getId_carrera()%>"><%= carrera.getNombre_carrera()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <button type="submit" class="btn  btn-right"><%= action.equals("update") ? "Actualizar" : "Registrar" %></button>
            </form>

        </div>
    </div>
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
