import entity.session.Session;
import server.dao.mysql.FilmMySQL;
import server.dao.mysql.SessionMySQL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/session_for_film")
public class SessionForFilm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        int id_film = Integer.parseInt(req.getParameter("id_film"));
        ArrayList<Session> sessions = FilmMySQL.getInstance().getSessionForFilm(id_film);
        req.setAttribute("sessions",sessions);
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/sessions_for_film.jsp");
        requestDispatcher.forward(req, resp);
    }
}
