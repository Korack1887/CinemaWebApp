import entity.film.Film;
import entity.film.Genre;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import server.dao.mysql.FilmMySQL;
import server.dao.mysql.MysqlDAOFactory;
import util.ConvertStringToDateSQL;
import util.ImageUtil;


import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/create_film")
@MultipartConfig
public class CreateFilmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/create_film.jsp");
        req.setAttribute("directors", FilmMySQL.getInstance().getAllDirector());
        req.setAttribute("genres", FilmMySQL.getInstance().getAllGenres());
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Genre> genres = new ArrayList<>();
        String[] asd = req.getParameterValues("genre_film");
        Part filePart = req.getPart("film_pic"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();
        BufferedImage bi = ImageIO.read(fileContent);
        bi = ImageUtil.resizeImage(bi,340,340);
        String path = req.getSession().getServletContext().getRealPath("/images")+"\\"+fileName;
        System.out.print(path);
        ImageIO.write(bi, "jpg", new File(path));
        for (String s: asd
             ) {
            genres.add(FilmMySQL.getInstance().getGenre(Integer.parseInt(s)));
        }
        Film newFilm = new Film.Builder()
                .name(req.getParameter("film_name"))
                .nameUa(req.getParameter("film_nameUa"))
                .description(req.getParameter("description"))
                .descriptionUa(req.getParameter("descriptionUa"))
                .date(ConvertStringToDateSQL.convert(req.getParameter("release")))
                .directors(FilmMySQL.getInstance().
                        getDirector(Integer.parseInt(req.getParameter("director_film"))))
                .duration(Integer.parseInt(req.getParameter("duration")))
                .genres(genres)
                .image(fileName)
                .build();
        FilmMySQL.getInstance().addFilm(newFilm);
    }
}
