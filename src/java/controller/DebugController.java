package controller;

import ejb.MovieEJB;
import ejb.ShowtimeEJB;
import ejb.TheaterEJB;
import entity.Movie;
import entity.Showtime;
import entity.Theater;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author ryan
 */
@Named(value = "debugController")
@RequestScoped
public class DebugController {

    @EJB private TheaterEJB theaterEJB;
    @EJB private MovieEJB movieEJB;
    @EJB private ShowtimeEJB showtimeEJB;
    
    /**
     *  Clears all entries from the database.
     */
    public void clearDB(){
        theaterEJB.clear();
        movieEJB.clear();
        showtimeEJB.clear();
    }
    
    /**
     *  Populates the database with sample data.
     */
    public void populateDB(){
        // POPULATE THEATERS
        String[] tName      = {"Cinemark Legacy",       "Cinemark Movies 10",   "SlumScreen 6"};
        String[] tStreet    = {"7201 N Central Expy",   "1818 Coit Rd",         "Broad St"};
        String[] tCity      = {"Plano",                 "Plano",                "Richardson"};
        String[] tState     = {"TX",                    "TX",                   "TX"};
        String[] tZip       = {"75025",                 "75025",                "75158"};
        Theater t;
        Movie m;
        Showtime s;
        
        for(int i = 0; i < tName.length; i++){
            t = new Theater();
            t.setName(tName[i]);
            t.setAddrStreet(tStreet[i]);
            t.setAddrCity(tCity[i]);
            t.setAddrState(tState[i]);
            t.setAddrZip(tZip[i]);
            theaterEJB.add(t);
        }
        
        // POPULATE MOVIES
        String[] mName  = {"Planet of the Apes",    "Tammy"};
        String[] mRate  = {"PG13",                  "R"};
        long[]  mDurr   = {130,                     97};
        
        for(int i = 0; i < mName.length; i++){
            m = new Movie();
            m.setName(mName[i]);
            m.setRating(mRate[i]);
            m.setDuration(mDurr[i]);
            movieEJB.add(m);
        }
        
        //s = new Showtime();
        //s.setMovie(movieEJB.get(1));
        //s.setTheater(theaterEJB.get(1));
        
        
    }
    
}
