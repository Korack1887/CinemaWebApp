package util;

import server.dao.DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetDAOForServlet extends HttpServlet {
    public static DAOFactory getDAO(ServletContext servletContext){
        DAOFactory dao;
        Properties properties = new Properties();
        File file  = new File(servletContext.getRealPath("/WEB-INF/settings.properties"));
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dao = DAOFactory.getDAOFactoryFromSettings(properties);
        return dao;
    }
}
