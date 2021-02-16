import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogOut extends HttpServlet {
    private static final Logger log = Logger.getLogger(LogOut.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        log.trace("doPost start");
        HttpSession session = req.getSession();
        session.invalidate();
        log.debug("session invalidated");
        resp.sendRedirect("sessions_for_week.jsp");
        log.trace("doPost finish");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
