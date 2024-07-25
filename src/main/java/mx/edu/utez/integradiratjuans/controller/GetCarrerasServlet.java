package mx.edu.utez.integradiratjuans.controller;

import mx.edu.utez.integradiratjuans.dao.CarreraDao;
import mx.edu.utez.integradiratjuans.model.Carrera;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/GetCarrerasServlet")
public class GetCarrerasServlet extends HttpServlet {

    private final CarreraDao carreraDao = new CarreraDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Carrera> carreras = carreraDao.getAll();
        String json = new Gson().toJson(carreras);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
