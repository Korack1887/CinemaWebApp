package server.dao;

import server.dao.mysql.MysqlDAOFactory;

public abstract class DAOFactory {

    // List of DAO types supported by the factory

    private static TypeDAO defaultFactory = TypeDAO.MySQL;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract FilmDAO getFilmDAO();

    public abstract HallDAO getHallDAO();

    public abstract OrderDAO getOrderDAO();

    public abstract SessionDAO getSessionDAO();

    public abstract UserDAO getUserDAO();



    public static DAOFactory getDAOFactory(TypeDAO whichFactory) {
        switch (whichFactory) {
            case MySQL:
                return new MysqlDAOFactory();
//		case TypeDAO.Mongo:
//			return new MongoDAOFactory();
            default:
                return null;
        }
    }

    public static DAOFactory getDAOFactory() {
        return getDAOFactory(defaultFactory);
    }

    public static void setDefaultFactory(TypeDAO whichFactory) {
        defaultFactory = whichFactory;
    }

}
