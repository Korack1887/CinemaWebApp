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
        Context initContext = null;
        try {
            initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
        return ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
