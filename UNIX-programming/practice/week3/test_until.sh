#! /bin/bash

echo -n "Enter number"

read x

until((num>$x))

do
	((sum += count))
	let count +=1
done

echo "Sum(1~$x) = $sum"

