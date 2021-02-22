package entity.hall;

import java.util.List;

/**
 * I`m so sorry i mixed up columns and rows
 * This is java bean for row entity
 */
public class Column {
    /**
     * id - row's id
     * price - price for row
     * hall - to which hall row belongs
     * seats - what seats this row has
     */
    int id;
    float price;
    Hall hall;
    List<Seat> seats;

    @Override
    public String toString() {
        return "Column{" +
                "id=" + id +
                ", price=" + price +
                ", hall=" + hall +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Column(float price, Hall hall, List<Seat> seats) {
        this.price = price;
        this.hall = hall;
        this.seats = seats;
    }

    public Column(int id, float price, Hall hall, List<Seat> seats) {
        this.id = id;
        this.price = price;
        this.hall = hall;
        this.seats = seats;
    }
}
