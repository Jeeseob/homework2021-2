#! /bin/bash

sum(){
	((sum = $1 + $2))
	return $sum
}

sum 10  20
echo the result is $?

exit 0

