/**
 * To manage movies
 */

import Enums.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {
    Map<City, List<Movie>> cityVsMovies;
    List<Movie> allMovies;

    MovieController(){
        cityVsMovies = new HashMap<>();
        allMovies = new ArrayList<>();
    }

    //ADD movie to a particular city, make use of cityVsMovies map
    void addMovie(Movie movie, City city){
        allMovies.add(movie); // add the movie to the movie list
        // if it is new movie city mapping, create new ArrayList, if not return the city
        List<Movie> movies = cityVsMovies.getOrDefault(city,new ArrayList<>()); // return city movies
        // add the movie to the city
        movies.add(movie);
        // add the city,movie pair to the
        cityVsMovies.put(city,movies);
    }

    Movie getMovieByName(String movieName){
        for (Movie movie: allMovies){
            if(movie.getMovieName().equals(movieName)){
                return movie;
            }
        }
        return null;
    }

    List<Movie> getMoviesByCity(City city){
        return cityVsMovies.get(city);
    }

    //REMOVE movie from a particular city, make use of cityVsMovies map

    List<Movie> deleteMovieFromCity(String movieName,City city){
        List<Movie> movies = cityVsMovies.get(city);
        movies.removeIf(movie -> movie.getMovieName().equals(movieName));// return city movies


        return movies;
    }

    //UPDATE movie of a particular city, make use of cityVsMovies map


    //CRUD operation based on Movie ID, make use of allMovies list

}
