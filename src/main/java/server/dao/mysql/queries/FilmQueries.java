package server.dao.mysql.queries;

public interface FilmQueries {
     String SQL_ADD_FILM = "INSERT INTO `film`\n" +
             "(`id_film`,`description`,`name`,`date_entry`,`time_duration`,`id_director`,`descriptionUa`,\n" +
             "`nameUa`,`image`) VALUES (?,?,?,?,?,?,?,?,?);";
     String SQL_ADD_GENRE_TO_FILM = "insert into film_genre values(?, ?)";
     String SQL_GET_FILM = "Select * from film where id_film = ?";
     String SQL_GET_ALL_FILM = "Select * from film";
     String SQL_GET_DIRECTOR = "Select * from director where id_director = ?";
     String SQL_GET_ALL_DIRECTOR = "Select * from director";
     String SQL_GET_ALL_GENRE = "Select * from genre";
     String SQL_GET_GENRE = "Select * from genre where id_genre = ?";
     String SQL_GET_DIRECTOR_FROM_FILM = "Select * from director " +
                                                  "join film on director.id_director = film.id_director" +
                                                  " where id_film = ?";
     String SQL_GET_GENRES_FROM_FILM = "Select * from genre" +
                                        " join  film_genre on film_genre.id_genre = genre.id_genre" +
                                        " where id_film = ?";
}
