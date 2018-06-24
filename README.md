# Challenge
The awesome Challenge to move value from user *a* to user *b* consistently *BUT* with no shared state

## How to Build
1. Go to root directory of the repo
2. Run `./mnvw clean install`

## How to run
1. Copy the jar `ripple-challenge-0.1.0.jar` from `repo_root/target` to whereever you would like.
2. Run two  servers (one for user *a* and one for user *b* ) as

`java -Dserver.port=8080 -Duserid=a -Dother="http://localhost:8079" -jar target/ripple-challenge-0.1.0.jar`
`java -Dserver.port=8079 -Duserid=b -Dother="http://localhost:8080" -jar target/ripple-challenge-0.1.0.jar`

3. It is better to run above two commands from two different terminals.

## Validation:
1. Based on above example:
   * Server running on port 8080 runs user "a" trustline (*userid* system property)
   * Server running on port 8079 runs user "b" trustline (*userid* system property)
   * Server running a's trustline _discovers_ b's endpoint also via the *"other"* system property and vice-versa.

2. Make a test request by running this from repo_root in a terminal shell.
   `./scripts/one_request_test.sh`
	
   This script will send one unit from _a to b_ on port `8080` and from _b to a_ on port `8079`. You will see the results in server logs.

## Understanding server logs

1. When you see a line like

	    "Making transfer id=ab1107497315410248&from=a&to=b&amountPennies=1 ..."

This indicates request was issued to this server to move value from a to b in an amount of 1

---
2. When you see a line like

"Trustline balance for a with b :-17"

This indicates the movement of value was successful on this server and trustline of a has been adjusted.

---
3. Similary when you see a line like

"Received  Ripple id=ba1107497374372233&from=b&to=a&amountPennies=1 ..."

This indicates value transfer was requested on some other server and you are getting notified about it. ( The Ripple Effect) 

---

## Tests
Check the scripts directory for various load and resiliency tests.

