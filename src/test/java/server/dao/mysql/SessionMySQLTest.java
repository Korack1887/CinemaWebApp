package server.dao.mysql;

import entity.film.Film;
import entity.session.Session;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import util.DateTimeUtil;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SessionMySQLTest {

    @Test
    void addSession() {
        Session newSession = new Session(44,HallMySQL.getInstance().getHall(1),FilmMySQL.getInstance().getFilm(999), new DateTime("2021-01-01"));
        SessionMySQL.getInstance().addSession(newSession);
        ArrayList<Session> qwe = SessionMySQL.getInstance().getSessionForFilm(999);
        Assert.assertEquals(newSession, SessionMySQL.getInstance().getSession(44));
        Assert.assertEquals(newSession, SessionMySQL.getInstance().getSessionForFilm(999).get(1));
        Assert.assertEquals(newSession, SessionMySQL.getInstance().getSessionForDay("01.01").get(0));
    }

    @Test
    void getSessionSeats() {
        Session newSession = new Session(44,HallMySQL.getInstance().getHall(1),FilmMySQL.getInstance().getFilm(999), new DateTime("2021-01-01"));
        ArrayList<Session> sessions = new ArrayList<>();
        sessions.add(newSession);
        HashMap<Session, Integer> exp = new HashMap<>();
        exp.put(newSession, 1);
        Assert.assertEquals(exp, SessionMySQL.getInstance().getSessionSeats(sessions));
    }
}