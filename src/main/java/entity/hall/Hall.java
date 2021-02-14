package entity.hall;

public class Hall {
    private int id;
    private int seats;
    private int columns;

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", seats=" + seats +
                ", columns=" + columns +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Hall(int id, int seats, int columns) {
        this.id = id;
        this.seats = seats;
        this.columns = columns;
    }
}
