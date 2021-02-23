package server.dao.mysql;

import entity.hall.Column;
import entity.hall.Hall;
import entity.hall.Seat;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class HallMySQLTest {


    @Test
    void getHall() {
        Hall exp = new Hall(1, 1, 1);
        Assert.assertEquals(HallMySQL.getInstance().getHall(1), exp);
    }

    @Test
    void getColumn() {
        ArrayList<Seat> seats = new ArrayList<>();
        seats.add(HallMySQL.getInstance().getSeatById(1));
        Column column = new Column(1, 1, HallMySQL.getInstance().getHall(1), seats);
        Assert.assertEquals(column,HallMySQL.getInstance().getColumn(1));
    }

    @Test
    void getColumns() {
        ArrayList<Seat> seats = new ArrayList<>();
        seats.add(HallMySQL.getInstance().getSeatById(1));
        Column column = new Column(1, 1, HallMySQL.getInstance().getHall(1), seats);
        Assert.assertEquals(column,HallMySQL.getInstance().getColumns().get(0));
    }

    @Test
    void getBookedSeats() {
        ArrayList<Seat> seats = new ArrayList<>();
        seats.add(HallMySQL.getInstance().getSeatById(1));
        seats.add(HallMySQL.getInstance().getSeatById(1));
        Assert.assertEquals(seats,HallMySQL.getInstance().getBookedSeats(SessionMySQL.getInstance().getSession(1)));
    }
}