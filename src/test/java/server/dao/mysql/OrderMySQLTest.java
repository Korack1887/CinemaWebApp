package server.dao.mysql;


import entity.Ticket;
import entity.order.Order;
import entity.order.OrderStatus;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;


class OrderMySQLTest {

    @Test
    void createOrder() {
        ArrayList<Ticket> tickets = new ArrayList();
        Ticket ticket = new Ticket(2,SessionMySQL.getInstance().getSession(1)
                , HallMySQL.getInstance().getSeatById(1), HallMySQL.getInstance().getColumn(1));
        tickets.add(ticket);
        Order order = new Order(1,tickets, OrderStatus.valueOf("PAID"), UserMySQL.getInstance().getUserByEmail("qwe")
                , Date.valueOf("2010-01-01"), 132);
        OrderMySQL.getInstance().createOrder(order);
    }

    @Test
    void reportForFilm() {
        ArrayList<Ticket> tickets = new ArrayList();
        Ticket ticket1 = new Ticket(2,SessionMySQL.getInstance().getSession(1)
                , HallMySQL.getInstance().getSeatById(1), HallMySQL.getInstance().getColumn(1));
        Ticket ticket2 = new Ticket(1,SessionMySQL.getInstance().getSession(1)
                , HallMySQL.getInstance().getSeatById(1), HallMySQL.getInstance().getColumn(1));
        tickets.add(ticket2);
        tickets.add(ticket1);
        Assert.assertEquals(tickets,OrderMySQL.getInstance().reportForFilm(999));
    }
}