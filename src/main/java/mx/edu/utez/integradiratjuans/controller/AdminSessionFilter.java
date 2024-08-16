package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.*;
import jakarta.servlet.Filter;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/Admin/error.jsp",
        "/Admin/indexAdmin.jsp",
        "/Admin/navbar.jsp",
        "/Admin/registarAlumno.jsp",
        "/Admin/registrarAdmin.jsp",
        "/Admin/registrarCarrera.jsp",
        "/Admin/registrarClase.jsp",
        "/Admin/registrarDivision.jsp",
        "/Admin/registrarDocente.jsp",
        "/Admin/registrarGrupo.jsp",
        "/Admin/registrarMateria.jsp",
        "/Admin/verAdmin.jsp",
        "/Admin/verAlumnos.jsp",
        "/Admin/verCarreras.jsp",
        "/Admin/verClases.jsp",
        "/Admin/verDivisiones.jsp",
        "/Admin/verDocente.jsp",
        "/Admin/verGrupos.jsp",
        "/Admin/verMaterias.jsp"
})
public class AdminSessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Obtener la sesión sin crear una nueva si no existe
        HttpSession session = httpRequest.getSession(false);

        // Checar si la sesión tiene un atributo “tipoSesion” con valor “admin”
        boolean isAdmin = false;
        if (session != null) {
            Object tipoSesion = session.getAttribute("tipoSesion");
            if (tipoSesion != null && "admin".equals(tipoSesion)) {
                isAdmin = true;
            }
        }

        if (isAdmin) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect("accesoDenegado.jsp");
        }
    }
}
