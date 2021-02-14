package entity.session;

import entity.film.Film;
import entity.hall.Hall;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Session {
    private int id;
    private Hall hall;
    private Film film;
    private DateTime dateTime;

    public Session(Hall hall, Film film, DateTime dateTime) {
        this.hall = hall;
        this.film = film;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        return "Session{" +
                "id=" + id +
                ", hall=" + hall +
                ", film=" + film +
                ", dateTime=" + dtfOut.print(dateTime) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public DateTime getDateTime() {

        return dateTime;
    }

    public String getDayAndMonth() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MM.dd");
        return fmt.print(dateTime);
    }

    public Session(int id, Hall hall, Film film, DateTime dateTime) {
        this.id = id;
        this.hall = hall;
        this.film = film;
        this.dateTime = dateTime;
    }
}
