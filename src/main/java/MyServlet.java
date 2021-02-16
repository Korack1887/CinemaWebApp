import entity.hall.Hall;
import entity.session.Session;
import org.joda.time.DateTime;
import server.dao.DAOException;
import server.dao.DAOFactory;
import server.dao.TypeDAO;
import util.DateTimeUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/hello")
public class MyServlet extends HttpServlet {
    DAOFactory dao = null;
    @Override
    public void init() {

        dao = DAOFactory.getDAOFactory(TypeDAO.MySQL);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        HttpServletRequest servletRequest =request;
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/index.jsp");
        request.setAttribute("films", DateTimeUtil.getDaysForWeek());
        requestDispatcher.forward(request, response);
        System.out.println();
    }


}
