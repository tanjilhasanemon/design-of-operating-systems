#!/bin/bash

echo "Select an option:"
echo "1. Show system uptime"
echo "2. Show disk usage"
echo "3. Exit"
read CHOICE

case $CHOICE in
    1)
        uptime
        ;;
    2)
        df -h
        ;;
    3)
        echo "Goodbye!"
        exit 0
        ;;
    *)
        echo "Invalid choice. Please choose 1, 2, or 3."
        ;;
esac
