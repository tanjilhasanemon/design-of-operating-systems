#!/bin/bash
SECRET_WORD="linux"
USER_GUESS=""

while [ "$USER_GUESS" != "$SECRET_WORD" ]
do
    echo "Guess the secret word:"
    read USER_GUESS
done

echo "Access granted! 🎉"
