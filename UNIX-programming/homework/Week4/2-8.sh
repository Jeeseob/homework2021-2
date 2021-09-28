#! /bin/bash

echo -n "Is is morning?"
read answer

case $answer in
	y* | Y* ) echo Good morning ;;
	n* | n* ) echo Have a good  day ;;
	*) echo Sorry your answedr si not propoer ;;
esac

exit 0

