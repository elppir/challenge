package trustline.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorResponseTest {

    @Test
    public void getMessage() {
        ErrorResponse e  = new ErrorResponse("test");
        assertEquals("Message is persisted", "test", e.getMessage());

    }
}