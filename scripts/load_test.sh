#!/bin/bash
## checks if a sequence of load on two servers change balance
## Net result after the test is that trustline should be as before

for i in `seq 1 500`; do ./test_client.sh a b 1 localhost:8080 ; done
for i in `seq 1 500`; do ./test_client.sh b a 1 localhost:8079 ; done
