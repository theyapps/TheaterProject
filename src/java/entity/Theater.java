/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

/**
 *
 * @author ryan
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Theater.findAll",
                query="SELECT t FROM Theater t"),
    @NamedQuery(name="Theater.findByZip",
                query="SELECT t FROM Theater t WHERE t.addrZip = :addrzip"),
    @NamedQuery(name="Theater.findById",
                query="SELECT t FROM Theater t WHERE t.id = :id")
}) 
public class Theater implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String addrStreet;
    private String addrCity;
    private String addrState;
    @Pattern(regexp="^[0-9]{5}$")
    private String addrZip;
    
    // Not currently populating from database.
    @OneToMany(fetch=FetchType.LAZY, targetEntity=Showtime.class, mappedBy="theater")
    private List<Showtime> showtimes;

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

    public String getAddrStreet() {
        return addrStreet;
    }

    public void setAddrStreet(String addrStreet) {
        this.addrStreet = addrStreet;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrState() {
        return addrState;
    }

    public void setAddrState(String addrState) {
        this.addrState = addrState;
    }

    public String getAddrZip() {
        return addrZip;
    }

    public void setAddrZip(String addrZip) {
        this.addrZip = addrZip;
    }    
    
    public String getAddr(){
        return String.format("%s %s, %s %s", 
                this.addrStreet, 
                this.addrCity, 
                this.addrState, 
                this.addrZip
        );
    }

    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(List<Showtime> showtimes) {
        this.showtimes = showtimes;
    }
    
    public void addShowtime(Showtime showtime){
        this.showtimes.add(showtime);
        if(showtime.getTheater() != this){
            showtime.setTheater(this);
        }
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
        if (!(object instanceof Theater)) {
            return false;
        }
        Theater other = (Theater) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "entity.Theater[ id=" + id + " ]";
    }
    
}
