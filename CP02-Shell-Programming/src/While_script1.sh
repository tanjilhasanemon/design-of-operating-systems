#!/bin/bash
COUNTER=1

while [ $COUNTER -le 5 ]
do
    echo "Count is: $COUNTER"
    ((COUNTER++)) # Increments the counter by 1
done
