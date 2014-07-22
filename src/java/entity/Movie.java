/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ryan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Movie.findAll",
            query = "SELECT m FROM Movie m"),
    @NamedQuery(name="Movie.findById",
                query="SELECT m FROM Movie m WHERE m.id = :id")
    
})
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String rating;
    private Long duration; // Stored as minutes

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Long getDuration() {
        return duration;
    }
    
    /**
     *
     * @return String for the duration of the movie.
     */
    public String getDurationStr(){
        return String.format("%d %s %d %s", 
                duration/60, 
                duration/60 > 1?"hours":"hour", 
                duration%60, 
                duration%60 > 1?"mins":"min");
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Movie[ id=" + id + " ]";
    }
    
}
