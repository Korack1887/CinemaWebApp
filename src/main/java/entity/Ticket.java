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

    public Ticket(Session session, Seat seat, Column column) {
        this.session = session;
        this.seat = seat;
        this.column = column;
    }

    public Ticket(int id, Session session, Seat seat, Column column) {
        this.id = id;
        this.session = session;
        this.seat = seat;
        this.column = column;
    }
}
