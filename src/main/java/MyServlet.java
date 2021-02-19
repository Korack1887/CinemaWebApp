import entity.film.Film;
import org.apache.log4j.Logger;
import server.dao.DAOFactory;
import server.dao.mysql.FilmMySQL;
import util.DateTimeUtil;
import entity.film.comparators.SortFilmsByName;
import util.GetDAOForServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/hello")
public class MyServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MyServlet.class);
    DAOFactory dao = null;
    boolean asc_order = true;

    @Override
    public void init() {
        log.trace("init start");
        dao = GetDAOForServlet.getDAO(this.getServletContext());
        log.trace("init finish");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace("doGet start");
        String language = (String) request.getSession().getAttribute("session_lang");
        String sort_content = (String) request.getSession().getAttribute("sort_content");
        if (request.getParameter("sort_content") != null) {
            sort_content = request.getParameter("sort_content");
        }
        log.debug("Check sorting parameters");
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/index15.jsp");
        if (sort_content != null && sort_content.equals("film")) {
            log.debug("Set sorting by film name");
            ArrayList<Film> films = FilmMySQL.getInstance().getFilmsForDate(DateTimeUtil.getDaysForWeek());
            String isAll = request.getParameter("all_film");
            if (isAll!=null && isAll.equals("true")){
                films = FilmMySQL.getInstance().getAllFilms();
                log.debug("Show all films");
            }
            if (asc_order) {
                films = SortFilmsByName.sortAsc(films, language);
                asc_order = false;
            } else {
                films = SortFilmsByName.sortDesc(films, language);
                asc_order = true;
            }
            request.setAttribute("films", films);
            request.getSession().setAttribute("sort_content", "film");
        } else {
            if(asc_order){
                request.setAttribute("dates", DateTimeUtil.getDaysForWeek());
                asc_order=false;
            }
            else {
                request.setAttribute("dates", DateTimeUtil.getDaysForWeekReversed());
                asc_order=true;
            }
            request.getSession().removeAttribute("sort_content");
            log.debug("Set sorting by default(date)");
        }
        requestDispatcher.forward(request, response);
        log.trace("doGet finish");
    }


}
