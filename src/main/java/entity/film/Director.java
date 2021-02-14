package entity.film;

public class Director {
    private int id;
    private String Name;

    public Director(int id, String name) {
        this.id = id;
        Name = name;
    }

    public Director() {
    }

    @Override
    public String toString() {
        return "Director{" +
                "Name='" + Name + '\'' +
                '}';
    }

    public Director(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
