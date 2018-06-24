package trustline.processors;

import trustline.models.Transfer;
import trustline.models.Trustline;
import trustline.models.TrustlineFactory;

public class SimpleCommitProcessor implements CommitProcessor {


    public SimpleCommitProcessor()
    {
    }

    @Override
    public void commitLog(Transfer transfer)
    {
        boolean found=false;
        Trustline trustline = TrustlineFactory.getTrustline(transfer.getFrom(), transfer.getTo());

        if ( trustline != null) {
            found=true;
            trustline.addDebt(transfer.getAmountPennies());
            System.out.println(trustline.toString());
        }

        trustline = TrustlineFactory.getTrustline(transfer.getTo(), transfer.getFrom());
        if ( trustline != null) {
            found=true;
            trustline.addCredit(transfer.getAmountPennies());
            System.out.println(trustline.toString());
        }

        // we shouldnt be expecting this error at this stage
        if ( !found)
        {
            throw new IllegalStateException();
        }
    }
}
