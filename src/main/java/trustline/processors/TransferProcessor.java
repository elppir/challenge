package trustline.processors;

import trustline.client.RipplePublisher;
import trustline.util.UnknownEntityException;
import trustline.models.Transfer;
import trustline.models.TrustlineFactory;

public class TransferProcessor implements TrustlineMessageProcessor {

    private Transfer transfer;
    private CommitProcessor committer;
    private RipplePublisher publisher;

    public TransferProcessor(Transfer m, CommitProcessor cp, RipplePublisher np)
    {
        this.transfer   = m;
        this.committer  = cp;
        this.publisher  = np;

    }



    @Override
    public void validateMessage() {
        if ( !TrustlineFactory.userExists(transfer.getFrom())) {
            throw new UnknownEntityException("Entity not found");
        }
    }


    @Override
    public void processMessage() {
        /*
        WARNING:
            This is a 2PC setup. Serious consistency challenges can occur if this is not thought out and executed correctly.
            One big challenge for ex is the unreliability of the network during publish. Ideally we should use an eventual
            consistency model to mitigate that, but the time I have, I will implement a sync and fail fast model which
            will try to limit the consistency drift as early as possible. Still no guarantees!

         */

        prepareLog();
        publishMessage();
        commitLog();
    }


    private void prepareLog()
    {
        System.out.println("Making transfer " + transfer.toString() + " ...");
    }

    private void  publishMessage()
    {
        publisher.sendMessage(transfer);
    }

    private void commitLog()
    {
        committer.commitLog(transfer);
    }
}
