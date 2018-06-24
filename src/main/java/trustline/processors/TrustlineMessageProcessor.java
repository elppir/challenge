package trustline.processors;

public interface TrustlineMessageProcessor {
    void validateMessage();

    void processMessage();
}
