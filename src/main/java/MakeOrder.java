import entity.Ticket;
import entity.order.Order;
import server.dao.mysql.OrderMySQL;
import server.dao.mysql.UserMySQL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/makeOrder")
public class MakeOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/orderDetails.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = new Order((List<Ticket>) req.getSession().getAttribute("tickets"),
        UserMySQL.getInstance().getUserByEmail((String) req.getSession().getAttribute("session_email")));
        OrderMySQL.getInstance().createOrder(order);
        req.getSession().removeAttribute("tickets");
    }
}
