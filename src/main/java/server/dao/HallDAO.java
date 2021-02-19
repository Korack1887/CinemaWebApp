package server.dao;

import entity.hall.Column;
import entity.hall.Hall;
import entity.hall.Seat;
import entity.session.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface HallDAO {
    Hall getHall(int id);
    Column getColumn(int id);
    List<Column> getColumns();
    Column unmapColumn(ResultSet rs);
    void unmapSeatsForColumn(Connection con, Column col) throws SQLException;
    List<Seat> getBookedSeats(Session session);
}
