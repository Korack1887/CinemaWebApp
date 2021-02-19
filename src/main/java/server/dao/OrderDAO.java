package server.dao;

import entity.Ticket;
import entity.order.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface OrderDAO {
    void createOrder(Order order);
    PreparedStatement mapOrder(PreparedStatement st, Order order) throws SQLException;
    PreparedStatement mapTicket(PreparedStatement st, Ticket ticket) throws SQLException;
}
