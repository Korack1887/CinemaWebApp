package server.dao.mysql;

import entity.film.Director;
import entity.film.Film;
import entity.film.Genre;
import org.apache.log4j.Logger;
import server.dao.FilmDAO;
import server.dao.mysql.queries.FilmQueries;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;

public class FilmMySQL implements FilmDAO,Connectable {
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
            con.setAutoCommit(false);
            con.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
            st = con.prepareStatement(FilmQueries.SQL_ADD_FILM, PreparedStatement.RETURN_GENERATED_KEYS);
            st = mapFilm(st, film);
            System.out.println(st.executeUpdate());
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                filmId = rs.getInt(1);
                log.debug("Inserted book id --> " + filmId);
            } else {
                log.error("No data inserted");
                throw new SQLException("addBook: No data inserted");
            }
            st = con.prepareStatement(FilmQueries.SQL_ADD_GENRE_TO_FILM);
            for (Genre g: film.getGenres()
                 ) {
                st.setInt(1,filmId);
                st.setInt(2, g.getId());
                st.addBatch();
            }
            st.executeBatch();
            con.commit();
        } catch (SQLException e) {
            MysqlDAOFactory.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
                MysqlDAOFactory.closeStatement(st);
                MysqlDAOFactory.close(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public Film getFilm(int id)  {
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = con.prepareStatement(FilmQueries.SQL_GET_FILM);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()){
                return unmapFilm(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return new Film();
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
        st.setString(9, "Later");
        //st.set smth to store picture path
        return st;
    }

    Film unmapFilm(ResultSet rs) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        try {
            con = getConnection();
            st = con.prepareStatement(FilmQueries.SQL_GET_DIRECTOR_FROM_FILM);
            st.setInt(1, rs.getInt("id_film"));
            resultSet = st.executeQuery();
            Director directors = new Director();
            while (resultSet.next()) {
                directors = new Director(resultSet.getInt("id_director"), resultSet.getString("name"));
            }
            List<Genre> genres = new ArrayList<>();
            st = con.prepareStatement(FilmQueries.SQL_GET_GENRES_FROM_FILM);
            st.setInt(1, rs.getInt("id_film"));
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                genres.add(new Genre(resultSet.getInt("id_genre"), resultSet.getString("name")));
            }
            return new Film.Builder().id(rs.getInt("id_film"))
                    .directors(directors)
                    .genres(genres)
                    .description(rs.getString("description"))
                    .name(rs.getString("name"))
                    .date(new Date(rs.getTimestamp("date_entry").getTime()))
                    .duration(rs.getInt("time_duration"))
                    .descriptionUa("descriptionUa")
                    .nameUa("nameUa")
                    .image("image")
                    .build();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            MysqlDAOFactory.closeResultSet(resultSet);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return new Film.Builder().name("error").build();
    }
    public List<Film> getAllFilms() {
        Connection con  = getConnection();
        Statement st = null;
        ResultSet rs = null;
        List<Film> result = new ArrayList<>();
        try {
            st = con.createStatement();
            rs = st.executeQuery(FilmQueries.SQL_GET_ALL_FILM);
            while (rs.next()) {
                result.add(unmapFilm(rs));
            }
        }
        catch (SQLException e) {
        e.printStackTrace();
        }finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return result;
    }
    public List<Director> getAllDirector() {
        Connection con  = getConnection();
        Statement st = null;
        ResultSet rs = null;
        List<Director> result = new ArrayList<>();
        try {
            st = con.createStatement();
            rs = st.executeQuery(FilmQueries.SQL_GET_ALL_DIRECTOR);
            while (rs.next()) {
                result.add(new Director(rs.getInt("id_director"), rs.getString("name")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return result;
    }
    public List<Genre> getAllGenres() {
        Connection con  = getConnection();
        Statement st = null;
        ResultSet rs = null;
        List<Genre> result = new ArrayList<>();
        try {
            st = con.createStatement();
            rs = st.executeQuery(FilmQueries.SQL_GET_ALL_GENRE);
            while (rs.next()) {
                result.add(new Genre(rs.getInt("id_genre"), rs.getString("name")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
    public Director getDirector(int id){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
        con = getConnection();
        st = con.prepareStatement(FilmQueries.SQL_GET_DIRECTOR);
        st.setInt(1, id);
        rs = st.executeQuery();
        while (rs.next()){
            return new Director(rs.getInt("id_director"), rs.getString("name"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
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
            con = getConnection();
            st = con.prepareStatement(FilmQueries.SQL_GET_GENRE);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()){
                return new Genre(rs.getInt("id_genre"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return new Genre(0, "error");
    }
}
