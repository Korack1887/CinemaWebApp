package entity.film.comparators;

import entity.film.Film;
import entity.film.comparators.FilmNameComparator;
import entity.film.comparators.FilmNameUaComparator;

import java.util.ArrayList;

public class SortFilmsByName {
    public static ArrayList<Film> sortAsc(ArrayList<Film> list, String language){
        ArrayList<Film> films = new ArrayList<>();
        if(language=="eng"){
            list.stream().sorted(new FilmNameComparator()).forEach(f->films.add((Film) f));
        }
        else {
            list.stream().sorted(new FilmNameUaComparator()).forEach(f->films.add((Film) f));
        }
        return films;
    }
    public static ArrayList<Film> sortDesc(ArrayList<Film> list, String language){
        ArrayList<Film> films = new ArrayList<>();
        if(language=="eng"){
            list.stream().sorted(new FilmNameComparator().reversed()).forEach(f->films.add((Film) f));
        }
        else {
            list.stream().sorted(new FilmNameUaComparator().reversed()).forEach(f->films.add((Film) f));
        }
        return films;
    }
}
