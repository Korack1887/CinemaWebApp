import org.apache.log4j.Logger;
import server.dao.DAOFactory;
import server.dao.mysql.UserMySQL;
import util.GetDAOForServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign_in")
public class Authorize extends HttpServlet {
    private static final Logger log = Logger.getLogger(Authorize.class);
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
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/signIn.jsp");
        requestDispatcher.forward(req, resp);
        log.trace("doGet finish");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        boolean check = dao.getUserDAO().checkUser(req.getParameter("email"), req.getParameter("pass"));
        if(check){
            req.getSession().setAttribute("ses_role", dao.getUserDAO().getUserByEmail(email).getRole().toString());
            req.getSession().setAttribute("session_email", email);
            resp.sendRedirect("/session_for_week");
        }
        else {
            resp.sendRedirect("/login");
        }
        System.out.println(req.getSession().getAttribute("ses_role"));
    }
}
