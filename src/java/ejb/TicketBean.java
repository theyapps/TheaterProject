/*
 * 
 */

package ejb;

import entity.Ticket;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ryan
 */
@Stateless
public class TicketBean {
    @PersistenceContext(unitName = "TheaterProjectPU")
    private EntityManager em;
    
    public Ticket add(Ticket t){
        em.persist(t);
        return t;
    }
    
    public void clear(){
        em.createQuery("DELETE FROM Ticket t").executeUpdate();
    }
}
