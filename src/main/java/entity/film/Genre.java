package entity.film;

public class Genre {
    private int id;
    private String Name;

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

    @Override
    public String toString() {
        return "Genre{" +
                "Name='" + Name + '\'' +
                '}';
    }

    public Genre(int id, String name) {
        this.id = id;
        Name = name;
    }
}
