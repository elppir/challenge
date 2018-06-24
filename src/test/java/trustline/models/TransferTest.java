package trustline.models;

import org.junit.Test;
import trustline.util.InvalidMessageException;

import static org.junit.Assert.*;

public class TransferTest {

    @Test
    public void testValidObject() {
        Transfer t  = new Transfer("a", "b", 1000);
        assertEquals("From", "a" ,t.getFrom());
        assertEquals("To", "b" ,t.getTo());
        assertEquals("amountPennies", 1000 ,t.getAmountPennies());
        assertNotNull("transfer_id not null", t.getId());
    }

    @Test(expected=InvalidMessageException.class)
    public void toAsNull() {
        Transfer t  = new Transfer("a", null, 1000);
    }

    @Test(expected=InvalidMessageException.class)
    public void fromAsNull() {
        Transfer t  = new Transfer(null, "b", 1000);
    }

    @Test
    public void zeroAmountIsOk() {
        Transfer t  = new Transfer("a", "b", 0);
        assertNotNull("Zero Amount is ok", t);
    }

    @Test
    public void negativeAmountIsOk() {
        Transfer t  = new Transfer("a", "b", -1);
        assertNotNull("Negative Amount is ok", t);
    }

    @Test
    public void reverse() {
        Transfer t  = new Transfer("a", "b", 1000);
        Transfer reverse = t.reverse();

        assertEquals("From", t.getTo() ,reverse.getFrom());
        assertEquals("To",t.getFrom() ,reverse.getTo());
        assertEquals("amountPennies", 1000 ,t.getAmountPennies());
        assertNotNull("transfer_id not null", t.getId());
    }
}