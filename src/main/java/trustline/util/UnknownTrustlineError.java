package trustline.util;

public class UnknownTrustlineError extends RuntimeException {
    public UnknownTrustlineError(String message)
    {
        super(message);
    }
}
