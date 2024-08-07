<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Pregunta</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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

        .header-card{
            text-align: start;
            margin-left: 40px;
            margin-top: 20px;
            max-width: 90%;
            height: 30%;
            border-radius: 15px;
            background: #B8DDC0;

        }


        .card{
            background: #F6FFFD;
            margin-top: 10px;
        }
        /* Preguntas y respuestas abierta */
        .input-group {
            display: flex;
            align-items: center; /* Alinea verticalmente el label y el input */
            background: #EDEDED;
            border-radius: 20px;
            width: 100%;
            border: 1px solid transparent;
            margin-top: 25px;
        }

        .form-label {
            font-weight: 500;
            margin-left: 10px; /* Espacio entre el label y el input */
            margin-top: 8px;

        }

        .optionsDiv{
            align-items: center;
            margin-left: 20px;
        }

        .form-input {
            flex: 1; /* Toma el espacio restante */
            border: none;
            background: #EDEDED;
            outline: none; /* Quitar el borde de enfoque del campo de texto */
            font-weight: 500;
        }

        .input-group:focus-within {
            border: 1px solid black;
        }

        .title-prg{
            font-weight: 500;
            font-size: 26px;
            margin-top: 10px;
            margin-left: 15px;
        }

        /*
       Tipo de pregunta
         */
        .title{
            margin-left: 2px;
            font-weight: 500;
            margin-top: 15px;
        }


        .form-group{
            margin-left: 10px;
        }
        .form-control{
            background: #EDEDED;
            border-radius: 15px;
            font-weight: 500;
        }

        /*options */
        .optionsDiv {
            margin-left: 20px;
        }

        .btn-si{
            display: flex;
            align-items: center;
            background-color: #9EEB92;
            border-radius: 50px;
            width: 170px;
            color: black;
            padding: 5px;
            margin-top: 20px;
            font-weight: 500;
            display: inline-block;
            margin-bottom: 20px;
            margin-left: 60px;
        }



        .option-input-group input[type="text"] {
            margin-left: 10px;
            flex-grow: 1;
            background: #EDEDED ;
            width: 650px;
            margin-left: 5%;
            max-width: 100%;
        }

        .form-check-input {
            accent-color: #81e6d3; /* Cambia el color de la selección */
            width: 15px; /* Ajusta el tamaño    del botón */
            height: 15px; /* Ajusta el tamaño del botón */
            margin-top: 5px;
            margin-bottom: 10px;
            margin-right: 10px;
        }

        /* botones */
        .btn-agr{
            background: #B8DDC0;
            border-radius: 20px;
            font-weight: 500;
            align-items: start;
        }

        .btn-vista{
            background: #CDFFF4;
            border-radius: 20px;
            font-weight: 500;
            max-width: 150px;
            width: 100%;
            margin-left: 5%;
        }




    </style>
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
            newQuestion.className = 'question border p-3 my-3';
            newQuestion.innerHTML =
                "<div class='input-group'>" +
                "<label class='form-label'>Pregunta " + (questionIndex + 1) + ":</label>" +
                "<input type='text' class='form-input' name='questions[" + questionIndex + "].pregunta' required>" +
                    "</div>" +
                "<div class='form-group'>" +
                "<label class='title'>Tipo de Pregunta:</label>" +
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
            <p class="title-prg">Crear Preguntas</p>
        </div>
        <div class="card-body">
            <form action="VistaPreviaServlet" method="post">
                <div id="questionsDiv"></div>
                <button type="button" class="btn btn-agr my-3" onclick="addQuestion()">Agregar Pregunta</button>
                <input type="submit" class="btn btn-vista" value="Vista Previa">
            </form>
        </div>
    </div>
</div>
<!-- Bootstrap JS y dependencias -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
