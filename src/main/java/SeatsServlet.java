
import server.dao.mysql.HallMySQL;
import server.dao.mysql.SessionMySQL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/seats")
public class SeatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/seats.jsp");
        int ses =  Integer.parseInt(req.getParameter("session_id"));
        req.setAttribute("session_id", ses);
        req.setAttribute("seats", HallMySQL.getInstance().getColumns());
        req.getSession().setAttribute("booked_seats", HallMySQL.getInstance().
                getBookedSeats(SessionMySQL.getInstance().getSession(ses)));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
