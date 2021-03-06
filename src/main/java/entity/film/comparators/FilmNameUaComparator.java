package entity.film.comparators;

import entity.film.Film;

import java.util.Comparator;

/**
 * Class for sorting films by they nameUa parameter
 */
public class FilmNameUaComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Film f1 = (Film) o1;
        Film f2 = (Film) o2;
        return f1.getNameUa().compareTo(f2.getNameUa());
    }
}
