/*
 * 
 */

package controller;

import ejb.TicketEJB;
import entity.Ticket;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import mock.CreditCard;

/**
 *
 * @author ryan
 */
@Named(value = "ticketController")
@RequestScoped
public class TicketController {
    @EJB private TicketEJB ticketEJB;
    
    private Ticket ticket;
    private List<Ticket> ticketList;
    
    private String cc, exp, auth;
    private Double amt;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }
    
    public String addTicket(){
        if(CreditCard.processTransaction(cc, exp, auth, amt)){
            ticket = ticketEJB.add(ticket);
            return "transaction_success.xhtml";
        }
        else{
            return "transaction_fail.xhtml";
        }
    }
}
