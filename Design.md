# Design

The basic interaction is based on a single entity `Transfer`. This records transfer of value (think money) from one user to another. The transfer can be negative or zero too. This transfer can happen either as a first transfer request OR a ripple effect of another remote transfer request.

Each transfer agent is running as a peer in a cluster of distributed other agents. Each agent (server here) is responsible for maintaining its trustline healthy and tries its best to notify other servers in the cluster who are resposible for the counterparty's trustline consistency.

A guaranteed successful transfer is essentially a two phase commit or a distributed transaction. The goal of the solution is not to solve or address that but reduce the likelihood of inconsistencies on Trustlines deviating from each other.

Focus is to maintain consistency between the two trustlines and make it as reliable as possible in the time we have. A simple test would be two reverse transfers should cancel each other out.

## External Interactions:

Two resources are exposed on the server endpoints. This is because trustlines can change in two ways on a server.

Lets say the server Sa is responsible for user *a*'s trustline:

1. A direct request to the server Sa to move value. ( *`/transfers/` endpoint* )
2. A "ripple" effect from a remote transaction. This happens when a is the counterparty for a _transfer_ request on some other server Sb responsible for user *b*'s trustline. Then Server Sa is notified to adjust its trustline based on a remote transfer request. ( *`/ripples/` endpoint*)

### Direct Request:
Quite naturally, the first request is made to resource `transfers` as a HTTP POST Request. It takes a json of the form
`{ "from" : "a", "to" : "b" , "amountPennies" : 11" }`

The successful response is 302 Redirect to the newly created transfer resource.

### The _Ripple_ effect:

During the handling of the request, its _ripple_ effect needs to be sent to the server of the counterparty. The end point is _discovered_ (currently via a system property) and used to _ripple_ the change forward. This ripple request takes the same  initial transfer object (now with an identifier) and tries to *`PUT`* that transfer object to the `ripples` resource on a remote server. PUT is chosen for idempotency and future retries. 

This Ripple request is then handled at that remote server and leads to  committing the transfer details on that server.  Thats leads to a successful response ( HTTP Status 200) back to the initial server.

A successful ripple response _commits_ the initial transfer request on the first server. A failed response aborts that request. A noticeable side effect is that "remote" side commits first while the source of transfer commits last. 

A future TODO could be to make this more reliable and have the ripple responses also piggyback commit watermarks of the remote side. 

Essentially using either the `transfers` or the `ripples` resource, we are propagating the `transfer` commit log in a somewhat consistent way.


## Internal Composition

There are various processors like TransferProcessor and RippleProcessor which deal with processing the actual requests on those end points. 


## Caveats
This is from one day of Spring learning. I wish I had known better ways to avoid making singletons or discovering remote trustlines in the code. This would have been my next item of work after some review.

