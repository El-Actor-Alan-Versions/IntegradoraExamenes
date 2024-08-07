package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.GrupoDao;
import mx.edu.utez.integradiratjuans.model.Grupo;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Docente/GetGruposServlet")
public class GetGruposServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        GrupoDao grupoDao = new GrupoDao();
        List<Grupo> grupos;
        try {
            grupos = grupoDao.getAll();
            String json = new Gson().toJson(grupos);
            out.print(json);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"Error retrieving groups.\"}");
        } finally {
            out.flush();
            out.close();
        }
    }
}
