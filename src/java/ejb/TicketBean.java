/*
 * 
 */

package ejb;

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
    private EntityManager entityManager;
    
    
}
