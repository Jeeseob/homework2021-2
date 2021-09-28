#! /bin/bash

num1=2
num2=1

while (($num1 <= 9))
	do
		while (($num2 <= 9))
			do
				let result=$num1*$num2
				printf "$result\t"
				let num2+=1
			done
	printf "\n"
	let num2=1
	let num1+=1
	done

exit 0


