package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.model.Preguntas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/EnviarRespuestas")
public class RegistrarPreguntasServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lista de preguntas (podrías haberla obtenido de la base de datos o del contexto)
        List<Preguntas> preguntas = new ArrayList<>();



        // Resto de la lógica para manejar las respuestas
    }
}
