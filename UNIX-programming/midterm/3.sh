# !/bin/bash

#midterm Q3

# 초기 변수 설정
let i=0
let j=1
let k=0

# i가 100보다 작은 경우 반복하도록 합니다.
while (($i<100))
do
	# i의 값을 출력합니다.
	echo "$i"
	# i와 j를 더한 값을 k에 넣고
	# 큰값 이었던 j를 i로, 더한 값인 k를 j 로 변경 합니다.
	let k=i+j
	let i=j
	let j=k
done


