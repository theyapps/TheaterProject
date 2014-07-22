/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import ejb.ShowtimeEJB;
import entity.Showtime;
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
@Named(value = "showtimeController")
@RequestScoped
public class ShowtimeController {
    @EJB
    private ShowtimeEJB showtimeEJB;   

    protected Showtime showtime = new Showtime();
    protected List<Showtime> showtimeList = new ArrayList<>();

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public List<Showtime> getShowtimeList() {
        return showtimeEJB.getAll();
    }
    
    public Showtime getShowtimeById() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        Long id;
        
        if(paramMap.get("id") != null){
            id = Long.valueOf(paramMap.get("id"));
            return showtimeEJB.get(id);
        }
        
        return null;
    }

    public void setShowtimeList(List<Showtime> showtimeList) {
        this.showtimeList = showtimeList;
    }

    public String addShowtime() {
        showtime = showtimeEJB.add(showtime);
        return "showtime_list.xhtml";
    }

}
