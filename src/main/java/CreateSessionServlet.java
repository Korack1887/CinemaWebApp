import entity.session.Session;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import server.dao.DAOFactory;
import server.dao.TypeDAO;
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
import java.util.List;


@WebServlet("/createSession")
public class CreateSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/createSession.jsp");
        String day = (String)req.getSession().getAttribute("day");
        List<Session> sessions = SessionMySQL.getInstance().getSessionForDay(day);
        DateTime minDate;
        DateTime maxDate = new DateTime(DateTime.now().getYear()
                , Integer.parseInt(day.split("\\.")[1]),
                Integer.parseInt(day.split("\\.")[0]),22,0);
        if(sessions.isEmpty()){
           minDate = new DateTime(DateTime.now().getYear()
                   , Integer.parseInt(day.split("\\.")[1]),
                   Integer.parseInt(day.split("\\.")[0]),0,0);
           minDate = minDate.plusHours(9);
        }
        else {
            Session last = sessions.get(sessions.size()-1);
            minDate=last.getDateTime();
            minDate = minDate.plusMinutes(20+last.getFilm().getDuration());
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm");
        req.setAttribute("minDate", fmt.print(minDate));
        req.setAttribute("maxDate", fmt.print(maxDate));
        req.setAttribute("films", FilmMySQL.getInstance().getAllFilms());
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory dao = DAOFactory.getDAOFactory(TypeDAO.MySQL);
        Session getSession = new Session(dao.getHallDAO().getHall(1),
                dao.getFilmDAO().getFilm(Integer.parseInt(req.getParameter("film_get"))),new DateTime(req.getParameter("date_get")));
        dao.getSessionDAO().addSession(getSession);
        resp.sendRedirect("/sessions_for_day");
    }
}
