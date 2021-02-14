package server.dao.mysql;
import server.dao.UserDAO;


public class UserMySQL implements Connectable, UserDAO {


    private static UserMySQL dao;

    public static UserMySQL getInstance() {
        log.trace("Try get instance.");
        if (dao == null) {
            log.trace("Instance not found. Create new.");
            dao = new UserMySQL();
        }
        return dao;
    }

}
