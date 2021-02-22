package server.dao.mysql;


import entity.film.Director;
import entity.film.Film;
import entity.film.Genre;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.mysql.queries.CreateTestDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class FilmMySQLTest {
    Film exp;
    Connection con;


    @BeforeEach
    public void setUp() {
        ArrayList<Genre> genres = new ArrayList<>();
        String[] clearDB = CreateTestDB.SQL_CREATE_TEST_DB.split(";");
        genres.add(new Genre(1, "qwe"));
        exp = new Film.Builder().name("qwe").date(Date.valueOf("2010-01-01"))
                .description("qwe").descriptionUa("qwe").directors(new Director(1, "qwe")).duration(123)
                .genres(genres).image("qwe").nameUa("qwe").id(999).build();
        con = MysqlDAOFactory.getConnection();
        Statement st = null;
        try {
            st = con.createStatement();
            for (String s : clearDB
            ) {
                st.addBatch(s);
            }
            st.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    void addFilm() {
        Film act;
        FilmMySQL.getInstance().addDirector(new Director(1, "qwe"));
        FilmMySQL.getInstance().addGenre(new Genre(1, "qwe"));
        FilmMySQL.getInstance().addFilm(exp);
        act = FilmMySQL.getInstance().getFilm(999);
        Assert.assertEquals(act, exp);
    }

    @Test
    void getFilmsForDate() {
    }

    @Test
    void getAllFilms() {
    }

    @Test
    void testGetAllFilms() {
    }

    @Test
    void getFilmsCount() {
    }

    @Test
    void getAllDirector() {
    }

    @Test
    void getAllGenres() {
        ArrayList<Genre> exp = new ArrayList<>();
        exp.add(new Genre(44, "asd"));
        exp.add(new Genre(11, "qwe"));
        Assert.assertEquals(exp, FilmMySQL.getInstance().getAllGenres());
    }

    @Test
    void updateFilm() {
    }

    @Test
    void deleteFilm() {
    }

    @Test
    void addDirector() {
        Director exp = new Director(12, "asd");
        FilmMySQL.getInstance().addDirector(exp);
        Director act = FilmMySQL.getInstance().getDirectorById(12);
        Assert.assertEquals(act, exp);
    }

    @Test
    void addGenre() {
        Genre exp = new Genre(7, "qwew");
        FilmMySQL.getInstance().addGenre(exp);
        Genre act = FilmMySQL.getInstance().getGenre(7);
        Assert.assertEquals(act, exp);
    }
}