/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Movie> getMovieList() {
        return movieEJB.getAll();
    }
    
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

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public String addMovie() {
        movie = movieEJB.add(movie);
        return "movie_list.xhtml";
    }
}
