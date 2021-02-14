package entity.hall;

public class Seat {
    int id;
    int price;
    Column column;

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", price=" + price +
                ", column=" + column +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public Seat(int id, int price, Column column) {
        this.id = id;
        this.price = price;
        this.column = column;
    }
}
