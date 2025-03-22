import Enums.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {
    Map<City, List<Theatre>> cityVsTheatre;
    List<Theatre> allTheatre;

    TheatreController(){
        cityVsTheatre = new HashMap<>();
        allTheatre = new ArrayList<>();
    }

    //ADD theatre to a particular city, make use of cityVsTheatre map
    void addTheatre(Theatre theatre,City city){
        // add theatre to allTheatre list
        allTheatre.add(theatre);

        // get city wise theatre
        List<Theatre> theatres = cityVsTheatre.getOrDefault(city,new ArrayList<>());
        theatres.add(theatre);
        cityVsTheatre.put(city,theatres);
    }

    // get all shows for a movie
    Map<Theatre,List<Show>> getAllShows(Movie movie, City city){
        //get all the theater of this city
        Map<Theatre, List<Show>> theatreVsShows = new HashMap<>();

        // get city theatres
        List<Theatre> theatres = cityVsTheatre.get(city);

        //filter the theatres which run this movie

        for(Theatre theatre: theatres){
            List<Show> givenMovieShows = new ArrayList<>();
            List<Show> shows = theatre.getShows(); // get theatre shows

            for(Show show : shows) { // iterate shows
                // get the movie shows and add it to our list
                if(show.getMovie().getMovieId()== movie.getMovieId()) {
                    givenMovieShows.add(show);
                }
            }
            // if we have found the movie shows, add it to the theatre mapping
            if(!givenMovieShows.isEmpty()) {
                theatreVsShows.put(theatre, givenMovieShows);
            }
        }

        return theatreVsShows;
    }
}
