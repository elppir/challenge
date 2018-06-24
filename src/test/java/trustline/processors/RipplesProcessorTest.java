package trustline.processors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trustline.models.Transfer;
import trustline.models.TrustlineFactory;

import static org.junit.Assert.*;

public class RipplesProcessorTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    // TODO: Need to work on these after removing static dependency on Trustlines
    /*
    @Test
    public void validateMessage() {

        Transfer t = new Transfer("a", "b", 1000);
        CommitProcessor cp = new CommitProcessor() {
            @Override
            public void commitLog(Transfer t) {
                //do nothing
            }
        };

        TrustlineFactory.getTrustline("b","a");
        RipplesProcessor rp = new RipplesProcessor(t, cp);
        rp.validateMessage();
        boolean reached=true;
        assertEquals("Notification works if Trustline exists for a user", true, reached);
    }

    @Test(expected=IllegalStateException.class)
    public void validateMessageIfSender() {

        Transfer t = new Transfer("a", "b", 1000);
        CommitProcessor cp = new CommitProcessor() {
            @Override
            public void commitLog(Transfer t) {
                //do nothing
            }
        };

        TrustlineFactory.getTrustline("a", "b");
        RipplesProcessor rp = new RipplesProcessor(t, cp);
        rp.validateMessage();
    }



    @Test
    public void processMessageSuccess() {
        Transfer t = new Transfer("a", "b", 1000);
        CommitProcessor cp = new CommitProcessor() {
            @Override
            public void commitLog(Transfer t) {
                //do nothing
            }
        };

        TrustlineFactory.getTrustline("b","a");
        assertEquals("Trustline value changed", 0, TrustlineFactory.getTrustline("b","a").getTrustlinePennies());
        RipplesProcessor rp = new RipplesProcessor(t,cp);
        rp.processMessage();
        assertEquals("Trustline value changed", 1000, TrustlineFactory.getTrustline("b", "a").getTrustlinePennies());
    }

    @Test
    public void processMessageFail() {
        Transfer t = new Transfer("a", "b", 1000);
        boolean exception = false;

        CommitProcessor cp = new CommitProcessor() {
            @Override
            public void commitLog(Transfer t) {
                throw new IllegalStateException("one of a and b should be existing");
            }
        };

        TrustlineFactory.getTrustline("b","a");
        assertEquals("Trustline value changed", 0, TrustlineFactory.getTrustline("b","a").getTrustlinePennies());
        RipplesProcessor rp = new RipplesProcessor(t,cp);
        try {
            rp.processMessage();
        }catch ( IllegalStateException e)
        {
            exception=true;
        }
        assertEquals("Trustline value didnt change", 0, TrustlineFactory.getTrustline("b","a").getTrustlinePennies());
        assertEquals("we received an exception",true, exception);
    }
    */
}