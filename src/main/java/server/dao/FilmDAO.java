package server.dao;

import entity.film.Director;
import entity.film.Film;
import entity.film.Genre;
import entity.session.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface FilmDAO {
    void addFilm(Film film);
    Film getFilm(int id);
    ArrayList<Film> getFilmsForDate(List<String> date);
    PreparedStatement mapFilm(PreparedStatement st, Film film) throws SQLException;
    Film unmapFilm(ResultSet rs);
    List<Film> getAllFilms(int page);
    ArrayList<Film> getAllFilms();
    List<Director> getAllDirector();
    List<Genre> getAllGenres();
    void updateFilm(Film film);
    void deleteFilm(int id);
    Director getDirectorById(int id);
    Genre getGenre(int id);
    void addDirector(Director director);
    void addGenre(Genre genre);
    int getFilmsCount();
}
