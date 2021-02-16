import entity.Ticket;
import entity.hall.Column;
import server.dao.mysql.HallMySQL;
import server.dao.mysql.SessionMySQL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/addToCart")
public class AddTicketToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Column col = HallMySQL.getInstance().getColumn(Integer.parseInt(req.getParameter("id_col")));
        int id_ses = Integer.parseInt(req.getParameter("ses_id"));
        List<Ticket> tickets = (List<Ticket>) req.getSession().getAttribute("tickets");
        if(tickets==null){
            tickets = new ArrayList<>();
        }
        Ticket newTicket = new Ticket(SessionMySQL.getInstance().getSession(id_ses),
                col.getSeats().get(Integer.parseInt(req.getParameter("id_seat"))-1), col);
        tickets.add(newTicket);
        req.getSession().setAttribute("tickets", tickets);
        resp.sendRedirect("/seats"+"?session_id="+id_ses);
    }
}
