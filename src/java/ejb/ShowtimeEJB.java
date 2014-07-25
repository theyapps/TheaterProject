/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Showtime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ryan
 */
@Stateless
public class ShowtimeEJB {
    @PersistenceContext(unitName = "TheaterProjectPU")
    private EntityManager em;

    public List<Showtime> getAll() {
        TypedQuery<Showtime> query = em.createNamedQuery("Showtime.findAll", Showtime.class);
        return query.getResultList();
    }
    
    public Showtime get(long id) {
        TypedQuery<Showtime> query = em.createNamedQuery("Showtime.findById", Showtime.class);
        query.setParameter("id", id);
        
        // Hack to prevent exception thrown when no result found
        List<Showtime> list = query.getResultList();
        if(list == null || list.isEmpty()){
            return null;
        }
        return query.getSingleResult();
    }

    public Showtime add(Showtime showtime) {
        em.persist(showtime);
        return showtime;
    }
    
    public void clear(){
        em.createQuery("DELETE FROM Showtime s").executeUpdate();
    }
    
}
