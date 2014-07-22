package controller;

import ejb.MovieEJB;
import entity.Movie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author ryan
 */
@Named(value = "movieController")
@RequestScoped
public class MovieController {
    @EJB
    private MovieEJB movieEJB;

    protected Movie movie = new Movie();
    protected List<Movie> movieList = new ArrayList<>();

    /**
     * Getter for this.movie
     * @return Current instance of movie
     */
    public Movie getMovie() {
        return movie;
    }
    
    /**
     * Setter for this.movie
     * @param movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
    /**
     * @return A list of all movies from database.
     */
    public List<Movie> getMovieList() {
        return movieEJB.getAll();
    }
    
    /**
     * Looks at get param "id" and finds movie of that ID if one exists
     * @return Instance of Movie if param "id" is valid otherwise null
     */
    public Movie getMovieByParamId() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        Long id;
        
        if(paramMap.get("id") != null){
            id = Long.valueOf(paramMap.get("id"));
            return movieEJB.get(id);
        }
        
        return null;
    }

    /**
     * Setter for this.movieList
     * @param movieList
     */
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
    
    /**
     * Adds movie via MovieEJB.
     * @return "movie_list.xhtml"
     */
    public String addMovie() {
        movie = movieEJB.add(movie);
        return "movie_list.xhtml";
    }
}
