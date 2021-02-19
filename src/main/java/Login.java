
import entity.user.Gender;
import entity.user.Role;
import entity.user.User;
import org.apache.log4j.Logger;
import server.dao.DAOFactory;
import server.dao.mysql.MysqlDAOFactory;
import server.dao.mysql.UserMySQL;
import util.ConvertStringToDateSQL;
import util.GetDAOForServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


@WebServlet("/login")
public class Login extends HttpServlet {
    DAOFactory dao = null;
    private static final Logger log = Logger.getLogger(Login.class);

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
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/Login2.jsp");
        requestDispatcher.forward(req, resp);
        log.debug("Get to the login page");
        log.trace("doGet finish");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromForm = new User(req.getParameter("role"), req.getParameter("gender"), req.getParameter("name")
                , req.getParameter("email"), req.getParameter("pass")
                , ConvertStringToDateSQL.convert(req.getParameter("birthday")));
        boolean validLogin = dao.getUserDAO().addUser(userFromForm);
        if (validLogin) {
            req.setAttribute("success", "Account creation is successful");
            resp.sendRedirect("/session_for_week");
        } else {
            resp.sendRedirect("/login");
        }
    }
}
