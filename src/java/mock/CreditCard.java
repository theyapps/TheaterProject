/*
 * 
 */

package mock;


/**
 *
 * @author ryan
 */
public class CreditCard {

    /**
     * Process Transaction 
     * Note: This method is a mock method so it only checks that the
     * data is properly formed doesn't attempt to actually process.
     * 
     * @param cc 16 digit credit card number.
     * @param exp Month and year of expiry in form mm/yy.
     * @param auth Three digit auth code from rear of card.
     * @param amt Amount to be charged.
     * @return true if success, false if failed
     */
    public static boolean processTransaction(String cc, String exp, 
            String auth, Double amt){
        if(!cc.matches("^[0-9]{16}$")) return false;
        if(!exp.matches("^[0-9]{2}/[0-9]{2}$")) return false;
        if(!auth.matches("^[0-9]{3}$")) return false;
            
        return true;
    }
}
