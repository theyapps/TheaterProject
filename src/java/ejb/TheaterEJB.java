/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Showtime;
import entity.Theater;
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
public class TheaterEJB {
    @PersistenceContext(unitName = "TheaterProjectPU")
    private EntityManager entityManager;

    public List<Theater> getAll() {
        TypedQuery<Theater> query = entityManager.createNamedQuery("Theater.findAll", Theater.class);
        return query.getResultList();
    }

    public List<Theater> getByZip(String zip) {
        TypedQuery<Theater> query = entityManager.createNamedQuery("Theater.findByZip", Theater.class);
        return query.setParameter("addrzip", zip).getResultList();
    }
    
    public Theater get(long id) {
        TypedQuery<Theater> query = entityManager.createNamedQuery("Theater.findById", Theater.class);
        query.setParameter("id", id);
        
        // Hack to prevent exception thrown when no result found
        // by returning null instead of throwing the exception.
        List<Theater> list = query.getResultList();
        if(list == null || list.isEmpty()){
            return null;
        }
        
        return query.getSingleResult();
    }

        
    public List<Showtime> getShowtimesByTheaterId(long id) {
        TypedQuery<Showtime> query = entityManager.createNamedQuery("Theater.getShowtimesByTheaterId", Showtime.class);
        query.setParameter("id", id);
        
        return query.getResultList();
    }
            
    public Theater add(Theater theater) {
        entityManager.persist(theater);
        return theater;
    }
    
    public void clear(){
        entityManager.createQuery("DELETE FROM Theater t").executeUpdate();
    }
}
