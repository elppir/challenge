#!/bin/bash
## checks if a parallel load on two servers change balance
## Net result after the test is that trustline should be as before

for i in `seq 1 500`; do ./one_request_test.sh & done

