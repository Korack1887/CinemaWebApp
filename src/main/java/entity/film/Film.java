package entity.film;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Film {
private int id;
private Director director;
private List<Genre> genres;
private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private String descriptionUa;
private String name;
private String nameUa;
private Date date;
private int duration;
private String image;



    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", directors=" + director +
                ", genres=" + genres +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", duration=" + duration + " minutes" +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector (Director director) {
        this.director = director;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescriptionUa() {
        return descriptionUa;
    }

    public void setDescriptionUa(String descriptionUa) {
        this.descriptionUa = descriptionUa;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public static class Builder {
        private Film newFilm;

        public Builder() {
            newFilm = new Film();
        }

        public Film.Builder id(int id) {
            newFilm.id = id;
            return this;
        }
        public Film.Builder directors(Director director) {
            newFilm.director = director;
            return this;
        }
        public Film.Builder genres(List<Genre> genres) {
            newFilm.genres = genres;
            return this;
        }
        public Film.Builder description(String description) {
            newFilm.description = description;
            return this;
        }
        public Film.Builder name(String name) {
            newFilm.name = name;
            return this;
        }
        public Film.Builder date(Date date) {
            newFilm.date = date;
            return this;
        }
        public Film.Builder duration(int duration) {
            newFilm.duration = duration;
            return this;
        }
        public Film.Builder descriptionUa(String descriptionUa) {
            newFilm.descriptionUa = descriptionUa;
            return this;
        }
        public Film.Builder nameUa(String nameUa) {
            newFilm.nameUa = nameUa;
            return this;
        }
        public Film.Builder image(String image_path) {
            newFilm.image = image_path;
            return this;
        }
        public Film build(){
            return newFilm;
        }

    }
}
