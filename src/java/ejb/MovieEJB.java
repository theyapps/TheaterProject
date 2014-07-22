/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Movie;
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
public class MovieEJB {
    @PersistenceContext(unitName = "TheaterProjectPU")
    private EntityManager entityManager;

    public List<Movie> getAll() {
        TypedQuery<Movie> query = entityManager.createNamedQuery("Movie.findAll", Movie.class);
        return query.getResultList();
    }
    
    public Movie get(long id) {
        TypedQuery<Movie> query = entityManager.createNamedQuery("Movie.findById", Movie.class);
        query.setParameter("id", id);
        
        // Hack to prevent exception thrown when no result found
        List<Movie> list = query.getResultList();
        if(list == null || list.isEmpty()){
            return null;
        }
        return query.getSingleResult();
    }

    public Movie add(Movie movie) {
        entityManager.persist(movie);
        return movie;
    }
    
    public void clear(){
        entityManager.createQuery("DELETE FROM Movie m").executeUpdate();
    }
    
}
