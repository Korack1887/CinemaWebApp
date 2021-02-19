import org.apache.log4j.Logger;
import server.dao.DAOFactory;
import util.DateTimeUtil;
import util.GetDAOForServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/session_for_week")
public class SessionWeek extends HttpServlet {
    private static final Logger log = Logger.getLogger(SessionWeek.class);
    DAOFactory dao = null;

    @Override
    public void init() {
        log.trace("init start");
        dao = GetDAOForServlet.getDAO(this.getServletContext());
        log.trace("init finish");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("doGet start");
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/sessions_for_week.jsp");
        req.setAttribute("dates", DateTimeUtil.getDaysForWeek());
        log.debug("Dates -->" + DateTimeUtil.getDaysForWeek());
        requestDispatcher.forward(req, resp);
        log.trace("doGet finish");
    }
}
