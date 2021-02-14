package entity.order;

import entity.Ticket;

import java.util.Map;

public class Order {
    int id;
    Map<Ticket,Integer> cart;
}
