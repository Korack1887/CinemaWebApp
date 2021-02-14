package server.dao;

import entity.session.Session;

import java.util.List;

public interface SessionDAO {
    void addSession(Session session);
    Session getSession(int id);
    List<Session> getAllSession();
    void updateSession(Session session);
    void deleteSession(int id);
}
