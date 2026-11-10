#!/bin/bash
echo "Enter a number:"
read NUM

if [ $NUM -gt 10 ]; then
    echo "The number is greater than 10."
else
    echo "The number is 10 or less."
fi
