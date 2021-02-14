package server.dao.mysql;

import org.apache.log4j.Logger;
import server.dao.OrderDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderMySQL implements OrderDAO {
    private static final Logger log = Logger.getLogger(OrderMySQL.class);

    private static OrderMySQL dao;

    public static synchronized OrderMySQL getInstance() {
        log.trace("Try get instance.");
        if (dao == null) {
            log.trace("Instance not found. Create new.");
            dao = new OrderMySQL();
        }
        return dao;
    }

    Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            log.trace("Try get connection from pool.");
            con = MysqlDAOFactory.getConnection();
        } catch (SQLException e) {
            log.error("Cannot get connection from pool. Try use DriverManager.");
//			con = MysqlDAOFactory.getDBConnection();
        }
        return con;

    }
}
