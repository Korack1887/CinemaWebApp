package server.dao.mysql;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import server.dao.*;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * <p>Concrete factory for MySQL database.</p>
 * <p>Contains methods for create each domain DAO and methods to manage database connection.</p>
 * @see DAOFactory
 * @author engsyst
 *
 */
public class MysqlDAOFactory extends DAOFactory {
	private static final Logger log = Logger.getLogger(MysqlDAOFactory.class);

	public MysqlDAOFactory() {
		BasicConfigurator.configure();
	}

	/**
	 * Create MySQL pooled connection using application context
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	static Connection getConnection() throws SQLException {
		log.trace("Start");
		Connection con;
		con = DBCPDataSource.getConnection();
		log.trace("Finish");
		return con;
	}

	/**
	 * Create MSSQL connection using DriverManager
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */

	public static void rollback(Connection con) {
		if (con != null) {
			try {
				log.debug("Try rollback.");
				con.rollback();
			} catch (SQLException e) {
				log.error("Can not rollback transaction.", e);
			}
		}
	}

	protected static void commit(Connection con) {
		try {
			log.debug("Try commit transaction");
			con.commit();
		} catch (SQLException e) {
			log.error("Can not commit transaction." + e);
			try {
				log.debug("Try rollback transaction.");
				con.rollback();
			} catch (SQLException e1) {
				log.error("Can not rollback transaction." + e1);
			}
		}
	}

	protected static void close(Connection con) {
		try {
			log.debug("Try close connection.");
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			log.error("Can not close connection." + e.getMessage());
		}
	}

	static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				log.debug("Try close statement");
				stmt.close();
			} catch (SQLException e) {
				log.error(e.getLocalizedMessage(), e);
			}
		}
	}

	static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				log.debug("try close resultSet");
				rs.close();
			} catch (SQLException e) {
				log.error(e.getLocalizedMessage(), e);
			}
		}
	}

	protected static void commitAndClose(Connection con) {
		commit(con);
		close(con);
	}

	@Override
	public UserDAO getUserDAO() {
		return UserMySQL.getInstance();
	}

	@Override
	public FilmDAO getFilmDAO() {
		return FilmMySQL.getInstance();
	}

	@Override
	public SessionDAO getSessionDAO() {
		return SessionMySQL.getInstance();
	}
	@Override
	public HallDAO getHallDAO() {
		return HallMySQL.getInstance();
	}
	@Override
	public OrderDAO getOrderDAO() {
		return OrderMySQL.getInstance();
	}
}
