/*
 * 
 */

package controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author ryan
 */
@Named(value = "ticketController")
@RequestScoped
public class TicketController {
    @EJB TicketEJB ticketEJB;
}
