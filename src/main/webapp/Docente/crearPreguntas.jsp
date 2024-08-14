<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Pregunta</title>
    <!-- Bootstrap CSS -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/crearPreguntas.css">
    <script>
        function showOptions(questionIndex) {
            var questionType = document.getElementById("questionType" + questionIndex).value;
            var optionsDiv = document.getElementById("optionsDiv" + questionIndex);
            optionsDiv.innerHTML = "";

            if (questionType == "opcion_multiple" || questionType == "varias_respuestas") {
                var addOptionBtn = "<button type='button' class='btn btn-si' onclick='addOption(" + questionIndex + ")'>Agregar Opción</button>";
                optionsDiv.innerHTML = addOptionBtn;
                addOption(questionIndex);
                addOption(questionIndex);
                addOption(questionIndex);
                addOption(questionIndex);
            } else if (questionType == "abierta") {
                optionsDiv.innerHTML = "<div class='input-group'><label class='form-label'>Respuesta:</label><input type='text' class='form-input' name='questions[" + questionIndex + "].openEndedAnswer' required></div>";
            } else if (questionType == "verdadero_falso") {
                optionsDiv.innerHTML =
                    "<div class='form-check '><input type='radio' class='form-check-input si' name='questions[" + questionIndex + "].correctOption' value='true' required> Verdadero</div>" +
                    "<div class='form-check '><input type='radio' class='form-check-input si' name='questions[" + questionIndex + "].correctOption' value='false' required> Falso</div>";
            }
        }

        // Añade una opción nueva para preguntas de opción múltiple
        function addOption(questionIndex) {
            var optionsDiv = document.getElementById("optionsDiv" + questionIndex);
            var numOptions = optionsDiv.getElementsByClassName('option').length + 1;
            var newOption = document.createElement('div');
            newOption.className = 'option form-group';
            newOption.innerHTML =
                "<label>Opción " + numOptions + ":</label>" +
                "<input type='text' class='form-control' name='questions[" + questionIndex + "].option" + numOptions + "' required>" +
                "<div class='form-check'><input type='checkbox'  class='form-check-input' name='questions[" + questionIndex + "].correctOption" + numOptions + "'> Correcta</div>";
            optionsDiv.insertBefore(newOption, optionsDiv.lastElementChild);
        }

        // Añade una nueva pregunta al formulario
        function addQuestion() {
            var questionsDiv = document.getElementById("questionsDiv");
            var questionIndex = questionsDiv.getElementsByClassName('question').length;
            var newQuestion = document.createElement('div');
            newQuestion.className = 'question  p-3 my-3';
            newQuestion.innerHTML =
                "<div class='input-group'>" +
                "<label class='form-label'>Pregunta " + (questionIndex + 1) + ":</label>" +
                "<input type='text' class='form-input' name='questions[" + questionIndex + "].pregunta' required>" +
                "</div>" +
                "<div class='form-group'>" +
                "<label class='title'></label>" +
                "<select id='questionType" + questionIndex + "' class='form-control' name='questions[" + questionIndex + "].questionType' onchange='showOptions(" + questionIndex + ")' required>" +
                "<option value=''>Seleccione el tipo de pregunta</option>" +
                "<option value='opcion_multiple'>Opción Múltiple</option>" +
                "<option value='abierta'>Abierta</option>" +
                "<option value='varias_respuestas'>Varias Respuestas</option>" +
                "<option value='verdadero_falso'>Verdadero/Falso</option>" +
                "</select>" +
                "</div>" +
                "<div  class='optionsDiv' id='optionsDiv" + questionIndex + "'></div>";
            questionsDiv.appendChild(newQuestion);
        }


        window.onload = function() {
            addQuestion();
        };
    </script>

    <style>
        @import url('https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');

        body{
            font-family: 'PT Sans';
            justify-content: center;
            align-items: center;
            background: #EEEEEE;
            width: 100%;
            height: 100%;
        }

        .container{
            margin-top: 20px;
        }

        /* Titulo */
        .header-card{
            background-color: #97E3D2;
            display: flex;
            width: 100%;
            height: 12px;
            border-top-left-radius: 6px;
            border-top-right-radius: 6px;
        }

        .title-prg{
            font-size: 32px;
            font-weight: 500;
        }

        /* Todas las cards */
        .card{
            margin: auto;
            box-shadow: 0 3px 5px rgba(0, 0, 0, 0.2);
            border-radius: 6px;
            width: 100%;
            max-width: 700px;
            margin-bottom: 20px;
        }
        /* Botones */
        .btn-agr{
            border-radius: 20px;
            font-weight: 530;
            width: 150px; /* Tamaño fijo */
            padding: 0.5em;
            box-sizing: border-box;
        }

        .btn-si{
            border-radius: 20px;
            font-weight: 530;
            width: 150px; /* Tamaño fijo */
            padding: 0.5em;
            box-sizing: border-box;
            background-color: #97e6d4;
        }





        @media (max-width: 768px) {
            .btn{
                margin-left: 20px;
                width: 34px;
            }

        }


        footer{
            height: 20%;
        }

        /* Cards restantes */
        .body{
            width: 100%;
        }



        .bord-label{
            margin-top: 40px;
        }

        .input-group {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .form-label {
            margin-right: 10px;
            font-weight: 500;
            margin-top: 8px;
        }

        .form-input {
            flex-grow: 1;
            border: none;
            border-bottom: 2px solid #ccc;
            outline: none;
            font-size: 16px;
        }
        /* Efecto de la linea */
        .form-input:focus {
            border-bottom-color: #6200EE;
        }

        /* select */
        .form-control{
            border-radius: 6px;
            color: gray;
            font-weight: 600;
        }

        select{
            border-radius: 6px;
            color: gray;
            font-weight: 600;
            background-color: #fff; /* Asegúrate de que el fondo sea blanco */
            background-image: url('data:image/svg+xml;charset=utf8,<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20"><path fill="none" stroke="%23222" stroke-width="2" d="M5 7l5 5 5-5"/></svg>'); /* Flecha personalizada */
            background-repeat: no-repeat;
            background-position: right 10px center; /* Posición de la flecha */
            background-size: 12px 12px; /* Tamaño de la flecha */
            padding-right: 30px; /* Espacio para la flecha */
            appearance: none; /* Elimina el estilo predeterminado del navegador */
            -webkit-appearance: none; /* Para Safari */
            -moz-appearance: none; /* Para Firefox */
        }

        option{
            font-family: 'PT Sans';
            font-weight: 800;
        }

        .no-outline:focus {
            outline: none;
        }

        .btn:focus {
            outline: none;
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
        <div class="header-card"></div>
        <div class="card-body">
            <div class="card-text"></div>
            <p class="title-prg ">Crear Preguntas</p>
            <!-- <img src="../img/crearExamen.png" width="68" alt="si"> -->
        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <div class="card-text">
                <form action="VistaPreviaServlet" method="post">
                    <div id="questionsDiv" class="body"></div>
                    <a type="button" title="Añadir pregunta" class="btn no-outline" onclick="addQuestion()">
                        <img src="../img/añadirPreg.png" alt="añadir" width="32">
                    </a>
                    <button type="submit" class="btn no-outline">
                        <img src="../img/vistaPrevia.png" alt="vista" width="32">
                    </button>

                </form>
            </div>
        </div>
    </div>
</div>
<footer>
    <br>
</footer>
<!-- Bootstrap JS y dependencias -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
