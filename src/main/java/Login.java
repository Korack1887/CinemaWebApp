
import entity.user.Gender;
import entity.user.Role;
import entity.user.User;
import server.dao.mysql.MysqlDAOFactory;
import server.dao.mysql.UserMySQL;
import util.ConvertStringToDateSQL;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/Login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromForm = new User(req.getParameter("role"), req.getParameter("gender"), req.getParameter("name")
                , req.getParameter("email"), req.getParameter("pass")
                , ConvertStringToDateSQL.convert(req.getParameter("birthday")));
        boolean validLogin = UserMySQL.getInstance().addUser(userFromForm);
        if(validLogin){
            req.setAttribute("success", "Account creation is successful");
            resp.sendRedirect("/session_for_week");
        }
        else {
            resp.sendRedirect("/login");
        }
    }
}
