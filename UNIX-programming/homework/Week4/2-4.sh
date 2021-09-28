#! /bin/bash


((result = $2+$1))
printf "$2 + $1 = $result\n"
((result = $2-$1))
printf "$2 - $1 = $result\n"
((result = $2*$1))
printf "$2 * $1 = $result\n"
((result = $2/$1))
printf "$2 / $1 = $result\n"


