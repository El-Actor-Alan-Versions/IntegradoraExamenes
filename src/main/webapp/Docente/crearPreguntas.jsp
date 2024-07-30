<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Pregunta</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script>
        // Muestra las opciones según el tipo de pregunta seleccionada
        function showOptions(questionIndex) {
            var questionType = document.getElementById("questionType" + questionIndex).value;
            var optionsDiv = document.getElementById("optionsDiv" + questionIndex);
            optionsDiv.innerHTML = "";

            if (questionType == "opcion_multiple" || questionType == "varias_respuestas") {
                var addOptionBtn = "<button type='button' class='btn btn-secondary' onclick='addOption(" + questionIndex + ")'>Agregar Opción</button>";
                optionsDiv.innerHTML = addOptionBtn;
                // Añadir 4 opciones por defecto
                addOption(questionIndex);
                addOption(questionIndex);
                addOption(questionIndex);
                addOption(questionIndex);
            } else if (questionType == "abierta") {
                optionsDiv.innerHTML = "<div class='form-group'><label>Respuesta:</label><input type='text' class='form-control' name='questions[" + questionIndex + "].openEndedAnswer' required></div>";
            } else if (questionType == "verdadero_falso") {
                optionsDiv.innerHTML =
                    "<div class='form-check'><input type='radio' class='form-check-input' name='questions[" + questionIndex + "].correctOption' value='true' required> Verdadero</div>" +
                    "<div class='form-check'><input type='radio' class='form-check-input' name='questions[" + questionIndex + "].correctOption' value='false' required> Falso</div>";
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
                "<div class='form-check'><input type='checkbox' class='form-check-input' name='questions[" + questionIndex + "].correctOption" + numOptions + "'> Correcta</div>";
            optionsDiv.insertBefore(newOption, optionsDiv.lastElementChild);
        }

        // Añade una nueva pregunta al formulario
        function addQuestion() {
            var questionsDiv = document.getElementById("questionsDiv");
            var questionIndex = questionsDiv.getElementsByClassName('question').length;
            var newQuestion = document.createElement('div');
            newQuestion.className = 'question border p-3 my-3';
            newQuestion.innerHTML =
                "<h3>Pregunta " + (questionIndex + 1) + "</h3>" +
                "<div class='form-group'><label>Pregunta:</label>" +
                "<textarea class='form-control' name='questions[" + questionIndex + "].pregunta' required></textarea></div>" +
                "<div class='form-group'><label>Tipo de Pregunta:</label>" +
                "<select id='questionType" + questionIndex + "' class='form-control' name='questions[" + questionIndex + "].questionType' onchange='showOptions(" + questionIndex + ")' required>" +
                "<option value=''>Seleccione el tipo de pregunta</option>" +
                "<option value='opcion_multiple'>Opción Múltiple</option>" +
                "<option value='abierta'>Abierta</option>" +
                "<option value='varias_respuestas'>Varias Respuestas</option>" +
                "<option value='verdadero_falso'>Verdadero/Falso</option>" + // Opción Verdadero/Falso
                "</select></div>" +
                "<div id='optionsDiv" + questionIndex + "'></div>";
            questionsDiv.appendChild(newQuestion);
        }

        // Inicializa la primera pregunta cuando la página carga
        window.onload = function() {
            addQuestion();
        };
    </script>
</head>
<body>
<div class="container">
    <h2>Crear Pregunta</h2>
    <form action="VistaPreviaServlet" method="post">
        <div id="questionsDiv"></div>

        <button type="button" class="btn btn-primary my-3" onclick="addQuestion()">Agregar Pregunta</button>
        <input type="submit" class="btn btn-success" value="Vista Previa">
    </form>
</div>
<!-- Bootstrap JS y dependencias -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
