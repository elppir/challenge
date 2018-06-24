package trustline.processors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trustline.models.Transfer;
import trustline.models.Trustline;
import trustline.models.TrustlineFactory;

import static org.junit.Assert.*;

public class SimpleCommitProcessorTest {

    // TODO: Need to work on these after removing static dependency on Trustlines

    /*
    @Test
    public void commitLogSuccess() {
        CommitProcessor cp = new SimpleCommitProcessor();
        Trustline a = TrustlineFactory.getTrustline("a","b");
        Trustline b = TrustlineFactory.getTrustline("b", "a");

        Transfer transfer = new Transfer("a", "b", 1000);
        assertEquals("initial_value",0,a.getTrustlinePennies());
        assertEquals("initial_value",0,b.getTrustlinePennies());

        cp.commitLog(transfer);

        assertEquals("initial_value",-1000,a.getTrustlinePennies());
        assertEquals("initial_value",1000,b.getTrustlinePennies());


        //tear down
        Transfer reverse = transfer.reverse();
        cp.commitLog(reverse);

    }


    @Test(expected = IllegalStateException.class)
    public void commitLogFailure() {
        CommitProcessor cp = new SimpleCommitProcessor();
        Trustline ab = TrustlineFactory.getTrustline("a", "b");
        Trustline ba = TrustlineFactory.getTrustline("b", "a");

        Transfer transfer = new Transfer("c", "d", 1000);
        assertEquals("initial_value",0,ab.getTrustlinePennies());
        assertEquals("initial_value",0,ba.getTrustlinePennies());

        cp.commitLog(transfer);
    }

    @Test(expected = IllegalStateException.class)
    public void commitLogFailureOnlySenderTrustlineWithMe() {
        CommitProcessor cp = new SimpleCommitProcessor();
        Trustline ab = TrustlineFactory.getTrustline("a", "b");
        Trustline ba = TrustlineFactory.getTrustline("b", "a");

        Transfer transfer = new Transfer("a", "d", 1000);
        assertEquals("initial_value",0,ab.getTrustlinePennies());
        assertEquals("initial_value",0,ba.getTrustlinePennies());

        cp.commitLog(transfer);
        assertEquals("initial_value",0,ab.getTrustlinePennies());
        assertEquals("initial_value",0,ba.getTrustlinePennies());
    }

    @Test(expected = IllegalStateException.class)
    public void commitLogFailureOnlyReceiverTrustLineWithMe() {
        CommitProcessor cp = new SimpleCommitProcessor();
        Trustline ab = TrustlineFactory.getTrustline("a", "b");
        Trustline ba = TrustlineFactory.getTrustline("b", "a");

        Transfer transfer = new Transfer("c", "b", 1000);
        assertEquals("initial_value",0,ab.getTrustlinePennies());
        assertEquals("initial_value",0,ba.getTrustlinePennies());

        cp.commitLog(transfer);
        assertEquals("initial_value",0,ab.getTrustlinePennies());
        assertEquals("initial_value",0,ba.getTrustlinePennies());
    }
    */

}