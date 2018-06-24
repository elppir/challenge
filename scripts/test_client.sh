#!/bin/bash
if [[ "$#" -lt 4 ]]; then
	echo ""
	echo "Usage: "
	echo "------"
	echo -e "\t${BASH_SOURCE} from to amountInPennies host:port "
	echo "Example:"
	echo "--------"
	echo -e "\t${BASH_SOURCE} a b 1 localhost:8080 "
	echo " "
	echo " will send request to add debt of 1 penny on a from b. Trustline of a is hosted at localhost:8080"
	echo " "
	exit
fi

from=$1
to=$2
amount=$3
location=$4
data="{\"from\":\"$1\",\"to\":\"$2\",\"amountPennies\":$3}"
#echo $data
curl -L --data $data  -H "Content-Type: application/json"  ${location}/transfers/
echo
