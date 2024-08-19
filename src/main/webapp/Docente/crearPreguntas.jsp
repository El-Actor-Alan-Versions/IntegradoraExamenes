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


</head>
<!-- Incluir el JS de Bootstrap y dependencias -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        $('.dropdown-toggle').dropdown();
    });
</script>
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
                    <button type="submit" title="Vista previa" class="btn no-outline">
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
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
