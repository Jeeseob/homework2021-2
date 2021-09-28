#! /bin/bash

# read user input
echo 'please input any string'

read x

echo "x: $x"

echo 'please input any string 2 times'

read x y

echo "x : $x"
echo "y : $y"

read -p 'please input any string : '

echo "input $REPLY"


