#! /bin/bash

#test string

echo "input Y or Other"

read ans

if [[$ans = [Yy]*]]
then
	echo "Yes"
else
	echo "Other"

fi
