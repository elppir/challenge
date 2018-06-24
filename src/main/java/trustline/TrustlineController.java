package trustline;

import java.util.HashMap;
import java.util.Map;

import trustline.client.HTTPRipplePublisher;
import trustline.models.ErrorResponse;
import trustline.util.InvalidMessageException;
import trustline.models.Transfer;
import trustline.processors.RipplesProcessor;
import trustline.processors.SimpleCommitProcessor;
import trustline.processors.TransferProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import trustline.util.UnknownEntityException;

@RestController
public class TrustlineController {

    static Map<String, Transfer> transfers ;
    static { transfers = new HashMap<String,Transfer>(); }


    @GetMapping("/transfers/{id}")
    public Transfer getTransferDetails(@PathVariable("id") String id)
    {
        Transfer transfer = TrustlineController.transfers.get(id);
        if ( transfer == null) throw new UnknownEntityException("No Entity found");
        return transfer;
    }


    @PutMapping("/ripples/{id}")
    public ResponseEntity handleRipples(@RequestBody Transfer transfer, @PathVariable("id") String id)
    {
        try {
            processRipples(transfer);
            return new ResponseEntity(HttpStatus.OK);
        }catch (UnknownEntityException e)
        {
            throw new UnknownEntityException("No 'to' entity(" + transfer.getTo() + ") at this location");
        }
    }

    @PostMapping("/transfers")
    public RedirectView handleTransfer(@RequestBody Transfer transfer)
    {
        try {
            processTransfer(transfer);
            transfers.put(transfer.getId(), transfer);
            RedirectView rv = new RedirectView();
            rv.setUrl("/transfers/" + transfer.getId());

            return rv;
        }catch(UnknownEntityException e) {
            throw new UnknownEntityException("No 'from' entity(" + transfer.getFrom() + ") at this location");
        }
    }

    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Some key attributes are missing. See @TODO link")
    @ExceptionHandler(InvalidMessageException.class)
    public ErrorResponse clientErrorHandler(InvalidMessageException e)
    {
        return new ErrorResponse(e.getMessage());
    }

    private void processRipples(Transfer transfer)
    {
        RipplesProcessor rp = new RipplesProcessor(transfer, new SimpleCommitProcessor()) ;
        rp.validateMessage();
        rp.processMessage();
    }

    private void processTransfer(Transfer transfer)
    {
        TransferProcessor tp = new TransferProcessor(
                    transfer,
                    new SimpleCommitProcessor(),
                    new HTTPRipplePublisher(System.getProperty("other")));
        tp.validateMessage();
        tp.processMessage();
    }
}

