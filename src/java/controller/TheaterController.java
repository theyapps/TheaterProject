/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.TheaterEJB;
import entity.Showtime;
import entity.Theater;
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
@Named(value = "theaterController")
@RequestScoped
public class TheaterController {
    @EJB
    private TheaterEJB theaterEJB;

    protected Theater theater = new Theater();
    protected List<Theater> theaterList = new ArrayList<>();

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public List<Theater> getTheaterList() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String zip = paramMap.get("zip");
        return zip != null ?
            theaterEJB.getByZip(zip):
            theaterEJB.getAll();
    }
    
    public Theater getTheaterById() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        Long id;
        
        if(paramMap.get("id") != null){
            id = Long.valueOf(paramMap.get("id"));
            return theaterEJB.get(id);
        }
        
        
        return null;
    }

    public List<Showtime> getShowtimesByTheaterId() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        Long id;
        
        if(paramMap.get("id") != null){
            id = Long.valueOf(paramMap.get("id"));
            return theaterEJB.getShowtimesByTheaterId(id);
        }
                
        return null;
    }
    
    public void setTheaterList(List<Theater> theaterList) {
        this.theaterList = theaterList;
    }

    public String addTheater() {
        theater = theaterEJB.add(theater);
        return "theater_list.xhtml";
    }
}
