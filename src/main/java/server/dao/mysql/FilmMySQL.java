package server.dao.mysql;

import entity.film.Director;
import entity.film.Film;
import entity.film.Genre;
import entity.session.Session;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import server.dao.FilmDAO;
import server.dao.mysql.queries.FilmQueries;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;

public class FilmMySQL implements FilmDAO, Connectable {
    private static final Logger log = Logger.getLogger(FilmMySQL.class);

    private static FilmMySQL dao;

    public static FilmMySQL getInstance() {
        log.trace("Try get instance.");
        if (dao == null) {
            log.trace("Instance not found. Create new.");
            dao = new FilmMySQL();
        }
        return dao;
    }

    public void addFilm(Film film) {
        int filmId;
        Connection con = getConnection();
        PreparedStatement st = null;
        try {
            log.debug("Try add film to db: film name=" + film.getName());
            con.setAutoCommit(false);
            con.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
            st = con.prepareStatement(FilmQueries.SQL_ADD_FILM, PreparedStatement.RETURN_GENERATED_KEYS);
            st = mapFilm(st, film);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                filmId = rs.getInt(1);
                log.debug("Inserted film id --> " + filmId);
            } else {
                log.error("No data inserted");
                throw new SQLException("addFilm: No data inserted");
            }
            st = con.prepareStatement(FilmQueries.SQL_ADD_GENRE_TO_FILM);
            for (Genre g : film.getGenres()
            ) {
                st.setInt(1, filmId);
                st.setInt(2, g.getId());
                st.addBatch();
            }
            st.executeBatch();
            MysqlDAOFactory.commit(con);
            log.debug("Film added successfully");
        } catch (SQLException e) {
            log.debug("Error while add film to db");
            MysqlDAOFactory.rollback(con);
        } finally {
            MysqlDAOFactory.setAutocommit(con, true);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
    }

    public Film getFilm(int id) {
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            log.debug("Try get film from db by id: " + id);
            st = con.prepareStatement(FilmQueries.SQL_GET_FILM);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                return unmapFilm(rs);
            }
        } catch (SQLException e) {
            log.debug("Error while getting film from db");
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        log.debug("No film with this id: " + id);
        return new Film();
    }

    public ArrayList<Film> getFilmsForDate(List<String> dates) {
        ArrayList<Film> result = new ArrayList<>();
        Connection con = getConnection();
        CallableStatement st = null;
        ResultSet rs = null;
        try {
            log.debug("Try get films by dates");
            st = con.prepareCall(FilmQueries.SQL_GET_FILMS_FOR_DATE);
            for (String s : dates
            ) {
                st.setString(1, s);
                rs = st.executeQuery();
                while (rs.next()) {
                    Film addedFilm = unmapFilm(rs);
                    if (!result.contains(addedFilm)) {
                        result.add(unmapFilm(rs));
                    }
                }
            }
        } catch (SQLException e) {
            log.debug("Error getting films for dates");
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return result;
    }

    public PreparedStatement mapFilm(PreparedStatement st, Film film) throws SQLException {
        st.setInt(1, film.getId());
        st.setString(2, film.getDescription());
        st.setString(3, film.getName());
        st.setDate(4, film.getDate());
        st.setInt(5, film.getDuration());
        st.setInt(6, film.getDirector().getId());
        st.setString(7, film.getDescriptionUa());
        st.setString(8, film.getNameUa());
        st.setString(9, film.getImage());
        return st;
    }

    public Film unmapFilm(ResultSet rs) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        try {
            log.debug("Try creating film object from result set");
            con = getConnection();
            log.debug("Getting directors from film id");
            st = con.prepareStatement(FilmQueries.SQL_GET_DIRECTOR_FROM_FILM);
            st.setInt(1, rs.getInt("id_film"));
            resultSet = st.executeQuery();
            Director directors = new Director();
            while (resultSet.next()) {
                directors = new Director(resultSet.getInt("id_director"), resultSet.getString("name"));
            }
            List<Genre> genres = new ArrayList<>();
            log.debug("Getting genres from film id");
            st = con.prepareStatement(FilmQueries.SQL_GET_GENRES_FROM_FILM);
            st.setInt(1, rs.getInt("id_film"));
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                genres.add(new Genre(resultSet.getInt("id_genre"), resultSet.getString("name")));
            }
            log.debug("Film created successfully");
            return new Film.Builder().id(rs.getInt("id_film"))
                    .directors(directors)
                    .genres(genres)
                    .description(rs.getString("description"))
                    .name(rs.getString("name"))
                    .date(new Date(rs.getTimestamp("date_entry").getTime()))
                    .duration(rs.getInt("time_duration"))
                    .descriptionUa(rs.getString("descriptionUa"))
                    .nameUa(rs.getString("nameUa"))
                    .image(rs.getString("image"))
                    .build();
        } catch (SQLException e) {
            log.debug("Error creating film from result set");
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(resultSet);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        log.debug("Film creating error");
        return new Film.Builder().name("error").build();
    }
    public ArrayList<Film> getAllFilms() {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Film> result = new ArrayList<>();
        try {
            log.debug("Try to get all films from db");
            st = con.createStatement();
            rs = st.executeQuery(FilmQueries.SQL_GET_ALL_FILM);
            while (rs.next()) {
                result.add(unmapFilm(rs));
            }
        } catch (SQLException e) {
            log.debug("Error getting all films from db");
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return result;
    }
    public ArrayList<Film> getAllFilms(int page) {
        int start = page*3;
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Film> result = new ArrayList<>();
        try {
            log.debug("Try to get all films from db");
            st = con.prepareStatement(FilmQueries.SQL_GET_ALL_FILM_PAGE);
            st.setInt(1,start);
            st.setInt(2,3);
            rs = st.executeQuery();
            while (rs.next()) {
                result.add(unmapFilm(rs));
            }
        } catch (SQLException e) {
            log.debug("Error getting all films from db");
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return result;
    }

    public int getFilmsCount(){
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            log.debug("Getting films count");
            st = con.createStatement();
            rs = st.executeQuery(FilmQueries.SQL_GET_FILM_COUNT);
            while (rs.next()){
                return rs.getInt("val");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return 0;
    }

    public List<Director> getAllDirector() {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        List<Director> result = new ArrayList<>();
        try {
            log.debug("Try to get all directors from db");
            st = con.createStatement();
            rs = st.executeQuery(FilmQueries.SQL_GET_ALL_DIRECTOR);
            while (rs.next()) {
                result.add(new Director(rs.getInt("id_director"), rs.getString("name")));
            }
        } catch (SQLException e) {
            log.debug("Error getting all directors from db");
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return result;
    }

    public List<Genre> getAllGenres() {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        List<Genre> result = new ArrayList<>();
        try {
            log.debug("Try get all genres from db");
            st = con.createStatement();
            rs = st.executeQuery(FilmQueries.SQL_GET_ALL_GENRE);
            while (rs.next()) {
                result.add(new Genre(rs.getInt("id_genre"), rs.getString("name")));
            }
        } catch (SQLException e) {
            log.debug("Error while getting all genres from db");
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return result;
    }

    public void updateFilm(Film film) {

    }

    public void deleteFilm(int id) {

    }

    public Director getDirectorById(int id) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            log.debug("Try to get director by id: "+id);
            con = getConnection();
            st = con.prepareStatement(FilmQueries.SQL_GET_DIRECTOR);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                return new Director(rs.getInt("id_director"), rs.getString("name"));
            }
        } catch (SQLException e) {
            log.debug("Error getting director by id");
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return new Director(0, "error");
    }

    @Override
    public Genre getGenre(int id) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            log.debug("Try get genre by id: "+id);
            con = getConnection();
            st = con.prepareStatement(FilmQueries.SQL_GET_GENRE);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                return new Genre(rs.getInt("id_genre"), rs.getString("name"));
            }
        } catch (SQLException e) {
            log.debug("Error getting genre by id");
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return new Genre(0, "error");
    }

    @Override
    public void addDirector(Director director) {
        Connection con = getConnection();
        PreparedStatement st = null;
        try {
            log.debug("Adding new director");
            st = con.prepareStatement(FilmQueries.SQL_ADD_DIRECTOR);
            st.setString(1, director.getName());
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
    }

    @Override
    public void addGenre(Genre genre) {
        Connection con = getConnection();
        PreparedStatement st = null;
        try {
            log.debug("Adding new director");
            st = con.prepareStatement(FilmQueries.SQL_ADD_GENRE);
            st.setString(1, genre.getName());
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
    }
}
