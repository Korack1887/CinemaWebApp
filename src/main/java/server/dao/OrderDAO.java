package server.dao;

import entity.Ticket;
import entity.order.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface OrderDAO {
    /**
     * Transaction that creates step-by-step 1)Order, 2)Tickets, 3)Order_has_tickets records in database
     *
     * @param order to put into database
     */
    void createOrder(Order order);

    PreparedStatement mapOrder(PreparedStatement st, Order order) throws SQLException;

    PreparedStatement mapTicket(PreparedStatement st, Ticket ticket) throws SQLException;
}
