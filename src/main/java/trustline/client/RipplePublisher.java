package trustline.client;

import trustline.models.Transfer;

public interface RipplePublisher {
    void sendMessage(Transfer transfer);

}
