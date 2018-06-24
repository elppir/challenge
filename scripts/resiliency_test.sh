#!/bin/bash
# This test runs series of complimentary good and bad requests.
# Finally ends with one known good request.
# In the end the trustline should be even for both a and b


# Assumption:  
# 1. Only two valid users a and b. c and d are invalid.
# 2. a is on localhost:8080 and b is on localhost:8079

# Tests:
# 1. self transfer: No net effect
# 2. invalid sender: No change in either trustline
# 3. Invalid receiver: No change in either trustline
# 4. Sender, receiver  trustline servers are swapped : No change in either trustline 


for user1 in  a b c d  ; do
  for user2 in  b a c d ; do
	for i in `seq 1 500`; do
	    ./custom_one_request_test.sh $user1 $user2
	done
  done
done

./one_request_test.sh

