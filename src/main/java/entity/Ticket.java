package entity;

import entity.hall.Column;
import entity.hall.Seat;
import entity.session.Session;

public class Ticket {
    int id;
    Session session;
    Seat seat;
    Column column;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", session=" + session +
                ", seat=" + seat +
                ", column=" + column +
                '}';
    }

    public Ticket(Session session, Seat seat, Column column) {
        this.session = session;
        this.seat = seat;
        this.column = column;
    }
    public String getTicketInfo(String lang){
        if(lang!=null && lang.equals("eng")){
            return "Movie: "+session.getFilm().getName()+" date "+session.getDayAndMonthAndTime()+" row "+column.getId()+" seat "+seat.getId()+";";
        }
        return  "Фільм: "+session.getFilm().getNameUa()+" Дата "+session.getDayAndMonthAndTime()+" Рядок "+column.getId()+" Місце "+seat.getId()+";";
    }

    public Ticket(int id, Session session, Seat seat, Column column) {
        this.id = id;
        this.session = session;
        this.seat = seat;
        this.column = column;
    }
}
