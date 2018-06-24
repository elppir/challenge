package trustline.processors;

import trustline.models.Transfer;

public interface CommitProcessor {
    void commitLog(Transfer t);
}
