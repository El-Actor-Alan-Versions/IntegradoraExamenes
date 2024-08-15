<%@ page import="mx.edu.utez.integradiratjuans.dao.GrupoDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Grupo" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.MateriaDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Materia" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.DocenteDao" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Docente" %>
<%@ page import="mx.edu.utez.integradiratjuans.model.Clase" %>
<%@ page import="mx.edu.utez.integradiratjuans.dao.ClaseDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Clase clase = null;
    String action = "insert";
    int id_clase = 0;
    // Si se pasa un idDivision en la URL, cargamos los datos para actualización
    String idClaseParam = request.getParameter("id_clase");
    if (idClaseParam != null && !idClaseParam.isEmpty()) {
        id_clase = Integer.parseInt(idClaseParam);
        action = "update";

        ClaseDao dao = new ClaseDao();
        try {
            clase = dao.getById(id_clase);
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
    <title><%= action.equals("update") ? "Actualizar" : "Registrar" %> Clase</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
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
</style>
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
            <h2 class="text-center"><%= action.equals("update") ? "Actualizar" : "Registrar" %> Clase</h2>
        </div>
        <div class="card-body">
            <form action="<%= action.equals("update") ? "actualizarClaseServlet" : "registrarClaseServlet"%>" method="post">
                <% if (action.equals("update")){ %>
                <input type="hidden" name="id_clase" value="<%= clase.getId_clase()%>">
                <% } %>
                <div class="label-input-group">
                    <label for="id_grupo">Grupo:</label>
                    <select id="id_grupo" class="form-control" name="id_grupo" required>
                        <option value="">Seleccione...</option>
                        <%
                            GrupoDao grupoDao = new GrupoDao();
                            List<Grupo> grupos = grupoDao.getAll();
                            for (Grupo grupo : grupos) {
                        %>
                        <option value="<%= grupo.getIdGrupo() %>"><%= grupo.getGradoGrupo() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>

                <div class="label-input-group">
                    <label for="id_materia">Materia:</label>
                    <select id="id_materia" class="form-control" name="id_materia" required>
                        <option value="">Seleccione...</option>
                        <%
                            MateriaDao materiaDao = new MateriaDao();
                            List<Materia> materias = materiaDao.getAll();
                            for (Materia materia : materias) {
                        %>
                        <option value="<%= materia.getIdMateria() %>"><%= materia.getNombreMateria() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>

                <div class="label-input-group">
                    <label for="matricula">Docente:</label>
                    <select id="matricula" class="form-control" name="matricula" required>
                        <option value="">Seleccione...</option>
                        <%
                            DocenteDao docenteDao = new DocenteDao();
                            List<Docente> docentes = docenteDao.getAll();
                            for (Docente docente : docentes) {
                        %>
                        <option value="<%= docente.getMatricula() %>"><%= docente.getNombre() %> <%= docente.getApellidoPaterno() %> <%= docente.getApellidoMaterno() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>

                <button type="submit" class="btn btn-right"><%= action.equals("update") ? "Actualizar" : "Registrar" %></button>
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath}/JS/bootstrap.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
