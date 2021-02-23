package server.dao.mysql;


import entity.film.Director;
import entity.film.Film;
import entity.film.Genre;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DateTimeUtil;

import java.sql.*;
import java.util.ArrayList;

class FilmMySQLTest {
    Film exp;

    @BeforeEach
    public void setUp() {
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1, "qwe"));
        exp = new Film.Builder().name("qwe").date(Date.valueOf("2010-01-01"))
                .description("qwe").descriptionUa("qwe").directors(new Director(1, "qwe")).duration(123)
                .genres(genres).image("qwe").nameUa("qwe").id(999).build();
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
        Assert.assertEquals(0,FilmMySQL.getInstance().getFilmsForDate(DateTimeUtil.getDaysForWeek()).size());
    }

    @Test
    void getAllFilms() {
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1, "qwe"));
        exp = new Film.Builder().name("qwe").date(Date.valueOf("2010-01-01"))
                .description("qwe").descriptionUa("qwe").directors(new Director(1, "qwe")).duration(123)
                .genres(genres).image("qwe").nameUa("qwe").id(999).build();
        Assert.assertEquals(exp,FilmMySQL.getInstance().getAllFilms().get(0));
    }


    @Test
    void getFilmsCount() {
        Assert.assertEquals(1,FilmMySQL.getInstance().getFilmsCount());
    }

    @Test
    void getAllDirector() {
        ArrayList<Director> exp = new ArrayList<>();
        exp.add(new Director(665,"asd"));
        exp.add(new Director(1,"qwe"));
        Assert.assertEquals(exp, FilmMySQL.getInstance().getAllDirector());
    }

    @Test
    void getAllGenres() {
        ArrayList<Genre> exp = new ArrayList<>();
        exp.add(new Genre(1, "qwe"));
        exp.add(new Genre(7, "qwew"));
        Assert.assertEquals(exp, FilmMySQL.getInstance().getAllGenres());
    }

    @Test
    void updateFilm() {
        Film oldFilm = FilmMySQL.getInstance().getFilm(999);
        oldFilm=oldFilm.updateFilm(oldFilm);
        FilmMySQL.getInstance().updateFilm(oldFilm);
        Assert.assertEquals(oldFilm, FilmMySQL.getInstance().getFilm(999));
    }

    @Test
    void deleteFilm() {
        Film newFilm = new Film.Builder()
                .image("qweqwe")
                .genres(FilmMySQL.getInstance().getAllGenres())
                .duration(1233)
                .directors(FilmMySQL.getInstance().getDirectorById(1))
                .date(Date.valueOf("2010-01-01"))
                .descriptionUa("ewqweq")
                .description("eqwewq")
                .nameUa("asdasd")
                .name("sadasd")
                .id(5)
                .build();
        FilmMySQL.getInstance().addFilm(newFilm);
        FilmMySQL.getInstance().deleteFilm(5);
    }

    @Test
    void addDirector() {
        Director exp = new Director(665, "asd");
        FilmMySQL.getInstance().addDirector(exp);
        Director act = FilmMySQL.getInstance().getDirectorById(665);
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