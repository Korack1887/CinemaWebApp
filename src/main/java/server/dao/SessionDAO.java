package server.dao;

import entity.session.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SessionDAO {
    void addSession(Session session);
    Session getSession(int id);
    List<Session> getSessionForDay(String s);
    Session unmapSession(ResultSet rs) throws SQLException;
    HashMap<Session, Integer> getSessionSeats(ArrayList<Session> sessions);
    List<Session> getAllSession();
    void updateSession(Session session);
    void deleteSession(int id);
    ArrayList<Session> getSessionForFilm(int id);
}
