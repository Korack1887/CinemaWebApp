import entity.film.Director;
import entity.film.Film;
import entity.hall.Hall;
import entity.session.Session;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.BasicConfigurator;
import org.joda.time.DateTime;
import server.dao.DAOException;
import server.dao.DAOFactory;
import server.dao.TypeDAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws DAOException, SQLException {
        System.out.println(System.getProperty("user.dir"));
    }
}
