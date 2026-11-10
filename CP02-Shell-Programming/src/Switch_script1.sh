#!/bin/bash

echo "Enter a day of the week (e.g., Sunday, Thursday):"
read DAY

case $DAY in
    "Sunday")
        echo "Back to work! ☕"
        ;;
    "Thursday")
        echo "Weekend is almost here! 🎉"
        ;;
    "Saturday" | "Friday")
        echo "It is the weekend! Relax. 😎"
        ;;
    *)
        echo "Just another regular weekday."
        ;;
esac
