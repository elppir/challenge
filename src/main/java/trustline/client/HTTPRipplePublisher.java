package trustline.client;

import trustline.models.Transfer;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import trustline.util.InvalidMessageException;
import trustline.util.UnknownTrustlineError;

import java.util.ArrayList;
import java.util.List;

public class HTTPRipplePublisher implements RipplePublisher {
    private static final String RIPPLES_PATH="/ripples";
    private String endpoint;

    public HTTPRipplePublisher(String other )
    {
        assert (other!= null);
        /*
            TODO 1. verify it is a valid URL
            TODO: seperate other from self: if ( other.equals(self)) { throw IllegalStateException();
         */

        endpoint=other;    // eg: http://localhost:8080/ripples/
    }

    @Override
    public void sendMessage(Transfer transfer) {

        ResponseEntity r = send(transfer);
        handleResponse(r);
    }

    private ResponseEntity<Object> send(Transfer transfer) {


        String rippleUrl = endpoint + RIPPLES_PATH + "/" + transfer.getId();


        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(mediaTypeList);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(transfer, requestHeaders);


        // Create the HTTP PUT request,
        Class<Object> c = null;
        ResponseEntity<Object> response = restTemplate.exchange(rippleUrl, HttpMethod.PUT, requestEntity, c);
        return response;
    }


    private void handleResponse(ResponseEntity<Object> response) {
        if (response == null)
            throw new UnknownTrustlineError("No response came from Ripple endpoint");
        else {
            switch (response.getStatusCode()) {
                case ACCEPTED:
                case OK:
                case CREATED:
                case NO_CONTENT:
                    return;
                case BAD_REQUEST:
                    throw new InvalidMessageException(response.toString());
                default:
                    throw new UnknownTrustlineError("Ripple not successful");
            }
        }
    }
}
