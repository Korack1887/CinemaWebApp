import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeLang")
public class ChangeLang extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang;
        lang = req.getParameter("lang");
        if(lang==null){
            req.getSession().removeAttribute("session_lang");
        }
        if(lang!=null&&lang.equals("eng")){
            req.getSession().setAttribute("session_lang","eng");
        }
        resp.sendRedirect("/hello");
    }
}
