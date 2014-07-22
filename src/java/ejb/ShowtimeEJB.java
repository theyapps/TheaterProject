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
    private EntityManager entityManager;

    public List<Showtime> getAll() {
        TypedQuery<Showtime> query = entityManager.createNamedQuery("Showtime.findAll", Showtime.class);
        return query.getResultList();
    }
    
    public Showtime get(long id) {
        TypedQuery<Showtime> query = entityManager.createNamedQuery("Showtime.findById", Showtime.class);
        query.setParameter("id", id);
        
        // Hack to prevent exception thrown when no result found
        List<Showtime> list = query.getResultList();
        if(list == null || list.isEmpty()){
            return null;
        }
        return query.getSingleResult();
    }
    
    public List<Showtime> getAllByTheaterId(long theaterId) {
        TypedQuery<Showtime> query = entityManager.createNamedQuery("Showtime.findAllByTheaterId", Showtime.class);
        query.setParameter("id", theaterId);
        
        return query.getResultList(); 
    }
    
    public List<Showtime> getAllByMovieId(long movieId) {
        TypedQuery<Showtime> query = entityManager.createNamedQuery("Showtime.findAllByMovieId", Showtime.class);
        query.setParameter("id", movieId);
        
        return query.getResultList(); 
    }

    public Showtime add(Showtime showtime) {
        entityManager.persist(showtime);
        return showtime;
    }
    
    public void clear(){
        entityManager.createQuery("DELETE FROM Showtime s").executeUpdate();
    }
    
}
