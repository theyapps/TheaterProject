/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author ryan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Showtime.findAll",
            query = "SELECT s FROM Showtime s"),
    @NamedQuery(name = "Showtime.findById",
            query = "SELECT s FROM Showtime s WHERE s.id = :id"),
})
public class Showtime implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY) 
    @JoinColumn(name="movieID")
    private Movie movie;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="theaterID")
    private Theater theater;

    @Temporal(javax.persistence.TemporalType.TIME)
    private Date showtime; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }
    
    public void setTheater(Theater theater) {
        this.theater = theater;
        if(!theater.getShowtimes().contains(this)){
            theater.getShowtimes().add(this);
        }
    }
    
    public Date getTime() {
        return showtime;
    }

    public void setTime(Date time) {
        this.showtime = time;
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
        if (!(object instanceof Showtime)) {
            return false;
        }
        Showtime other = (Showtime) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Showtime[ id=" + id + " ]";
    }

}
