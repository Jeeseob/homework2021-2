#! /bin/bash

echo -n "Input x: "

read x

echo -n "Input y : "

read y

if (( x< y))
then
	echo $x is less than #y
else
	echo $y is less than $x

fi


