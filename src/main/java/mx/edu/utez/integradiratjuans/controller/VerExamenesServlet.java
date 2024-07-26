package mx.edu.utez.integradiratjuans.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.ExamenDao;
import mx.edu.utez.integradiratjuans.dao.ClaseDao;
import mx.edu.utez.integradiratjuans.model.Examen;
import mx.edu.utez.integradiratjuans.model.Clase;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Docente/VerExamenesServlet")
public class VerExamenesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExamenDao examenDao = new ExamenDao();
        ClaseDao claseDao = new ClaseDao();
        try {
            List<Examen> examenes = examenDao.getAll();
            Map<Integer, String> clasesMap = new HashMap<>();

            // Llenar el mapa de clases
            for (Examen examen : examenes) {
                if (!clasesMap.containsKey(examen.getId_clase())) {
                    Clase clase = claseDao.getById(examen.getId_clase());
                    if (clase != null) {
                        clasesMap.put(clase.getId_clase(), clase.getNombre());
                    }
                }
            }

            request.setAttribute("examenes", examenes);
            request.setAttribute("clasesMap", clasesMap);
            request.getRequestDispatcher("verExamenes.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al recuperar los exámenes.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
