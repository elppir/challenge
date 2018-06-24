#!/bin/bash
./test_client.sh $1 $2 1 http://localhost:8080 &
./test_client.sh $1 $2 1 http://localhost:8079 &
