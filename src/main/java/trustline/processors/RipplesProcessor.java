package trustline.processors;

import trustline.util.UnknownEntityException;
import trustline.models.Transfer;
import trustline.models.TrustlineFactory;

public class RipplesProcessor implements TrustlineMessageProcessor {

    Transfer transfer;
    CommitProcessor cp;

    public RipplesProcessor(Transfer m, CommitProcessor cp )
    {
        this.transfer=m;
        this.cp=cp;
    }

    @Override
    public void validateMessage() {

        if ( !TrustlineFactory.userExists(transfer.getTo())) {
            throw new UnknownEntityException("User Entity not found");
        }
    }

    /**
     * No republishing of a received notification.
     */
    @Override
    public void processMessage() {
        prepareLog();
        commitLog();
    }

    private void prepareLog()
    {
        // Do Nothing for now
        System.out.println("Received  Ripple " + transfer.toString() + " ...");
    }



    private void commitLog()
    {
        cp.commitLog(transfer);
    }
}
