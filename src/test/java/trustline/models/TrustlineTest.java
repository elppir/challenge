package trustline.models;

import org.junit.Test;
import trustline.util.InvalidMessageException;

import static org.junit.Assert.*;

public class TrustlineTest {

    @Test
    public void vaildObject() {
        Trustline t = new Trustline("a","b");
        assertNotNull("trustline not null", t);
        assertEquals("Userid persisted", "a", t.getUserId());
        assertEquals("amountPennies init to 0", 0, t.getTrustlinePennies());
    }

    @Test(expected = InvalidMessageException.class)
    public void nullUserIdShouldFail() {
        Trustline t = new Trustline(null,"b");
    }

    @Test(expected = InvalidMessageException.class)
    public void emptyUserIdShouldFail() {
        Trustline t = new Trustline("","b");
    }

    @Test
    public void addDebt() {
        Trustline t = new Trustline("a","b");
        assertEquals("initial_balance", 0, t.getTrustlinePennies());
        t.addDebt(10);
        assertEquals("final_balance", -10, t.getTrustlinePennies());
    }

    @Test
    public void addCredit() {
        Trustline t = new Trustline("a","b");
        assertEquals("initial_balance", 0, t.getTrustlinePennies());
        t.addCredit(10);
        assertEquals("final_balance", 10, t.getTrustlinePennies());
    }


    @Test
    public void debitAndCreditCancel() {
        Trustline t = new Trustline("a","b");
        assertEquals("initial_balance", 0, t.getTrustlinePennies());
        t.addCredit(10);
        t.addDebt(10);
        assertEquals("final_balance", 0, t.getTrustlinePennies());
    }

    @Test
    public void NegativeDebtSameAsCredit() {
        Trustline t = new Trustline("a","b");
        assertEquals("initial_balance", 0, t.getTrustlinePennies());
        t.addDebt(-10);
        assertEquals("final_balance", 10, t.getTrustlinePennies());
    }

    @Test
    public void zeroDebtOk() {
        Trustline t = new Trustline("a", "b");
        assertEquals("initial_balance", 0, t.getTrustlinePennies());
        t.addDebt(0);
        assertEquals("final_balance", 0, t.getTrustlinePennies());
    }

    @Test
    public void NegativeCreditSameAsDebit() {
        Trustline t = new Trustline("a", "b");
        assertEquals("initial_balance", 0, t.getTrustlinePennies());
        t.addCredit(-10);
        assertEquals("final_balance", -10, t.getTrustlinePennies());
    }

    @Test
    public void zeroCreditOk() {
        Trustline t = new Trustline("a", "b");
        assertEquals("initial_balance", 0, t.getTrustlinePennies());
        t.addCredit(0);
        assertEquals("final_balance", 0, t.getTrustlinePennies());
    }
}