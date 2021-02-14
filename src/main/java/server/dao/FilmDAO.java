package server.dao;

import entity.film.Director;
import entity.film.Film;
import entity.film.Genre;

import java.sql.SQLException;
import java.util.List;

public interface FilmDAO {
    void addFilm(Film film) throws SQLException;
    Film getFilm(int id);
    List<Film> getAllFilms() throws SQLException;
    void updateFilm(Film film);
    void deleteFilm(int id);
    Director getDirector(int id);
    Genre getGenre(int id);
}
