package server.dao.mysql;

import org.apache.log4j.Logger;
import server.dao.DBCPDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface Connectable {
    Logger log = Logger.getLogger(HallMySQL.class);

    default Connection getConnection(){
        try {
            return MysqlDAOFactory.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
