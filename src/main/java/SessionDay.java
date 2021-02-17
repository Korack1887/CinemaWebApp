import entity.session.Session;
import entity.session.comparators.SortSessionByDate;
import entity.session.comparators.SortSessionByFilmName;
import entity.session.comparators.SortSessionBySeats;
import server.dao.mysql.SessionMySQL;
import util.DateTimeUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet("/sessions_for_day")
public class SessionDay extends HttpServlet {
    boolean asc_order=true;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = (String) req.getSession().getAttribute("session_lang");
        String sort_content = (String) req.getSession().getAttribute("sort_content");
        ArrayList<Session> sessions = (ArrayList<Session>) SessionMySQL.getInstance().getSessionForDay(req.getParameter("day_of_week"));
        if (req.getParameter("sort_content") != null) {
            sort_content = req.getParameter("sort_content");
        }
        if (sort_content != null && sort_content.equals("film")) {
            if (asc_order) {
                sessions = SortSessionByFilmName.sortAsc(sessions,language);
                asc_order = false;
            } else {
                sessions = SortSessionByFilmName.sortDesc(sessions,language);
                asc_order = true;
            }
            req.getSession().setAttribute("sort_content", "film");
        }
        if (sort_content != null && sort_content.equals("seat")){
            HashMap<Session,Integer> sessionsSeat;
            sessionsSeat = SessionMySQL.getInstance().getSessionSeats(sessions);
            if (asc_order){
                sessionsSeat = SortSessionBySeats.sortAsc(sessionsSeat);
                asc_order = false;
            } else {
                sessionsSeat = SortSessionBySeats.sortDesc(sessionsSeat);
                asc_order = true;
            }
            req.getSession().setAttribute("sort_content", "seat");
            req.setAttribute("sessionsSeat", sessionsSeat);
        }
        if (sort_content != null && sort_content == "") {
            if (asc_order){
                sessions = SortSessionByDate.sortAsc(sessions);
                asc_order = false;
            } else {
                sessions = SortSessionByDate.sortDesc(sessions);
                asc_order = true;
            }
            req.getSession().removeAttribute("sort_content");
        }
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/sessions_for_day.jsp");
            req.getSession().setAttribute("day", req.getParameter("day_of_week"));
            req.setAttribute("sessions", sessions);
            requestDispatcher.forward(req, resp);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
