#include <stdio.h>
#include <stdlib.h>

// 학생 데이터를 저장할 구조체
typedef struct Student {
	char name[10];
	int korean;
	int english;
	int math;
}student;

int main(void)
{
	student* students;
	FILE* readFd; 
	FILE* writeFd; 
	char str[255];
	int num = 0;

	int TotalScoreKo = 0;
	int TotalScoreEng = 0;
	int TotalScoreMath = 0;



	readFd = fopen("3-8.dat","r");
	writeFd = fopen("3-8.out","w");

	while(fgets(str, 256, readFd) != NULL) {
		num++;
	}

	// 커서 초기화	
	rewind(readFd);
	// 과목명 읽어오기(버리는 값)	
	fgets(str, 256, readFd);

	students = (student*)malloc(sizeof(student) * (num-1));

	// 값 읽어오기.
	for (int i = 0; i < num-1; i++) {
		fscanf(readFd,"%s	%d %d %d", 
			students[i].name, &students[i].korean, &students[i].english, &students[i].math);
	}

	for (int i = 0; i < num-1; i++) {
		// 각 학생별 점수 출력
		fprintf(writeFd,"student name : %s, total score : %d, avg scroe : %d\n", 
			students[i].name, students[i].korean + students[i].english + students[i].math, 
			(students[i].korean + students[i].english + students[i].math)/3);

		// 과목 평균 계산을 위한 과목별 점수 더하기
		TotalScoreKo = students[i].korean + TotalScoreKo;
		TotalScoreEng = students[i].english + TotalScoreEng;
		TotalScoreMath = students[i].math + TotalScoreMath;
	}

	//과목 평균 쓰기
	fprintf(writeFd,"avg score of korean = %d\navg scroe of english = %d\navg scroe of math = %d\n",
		TotalScoreKo/(num-1), TotalScoreEng/(num-1),TotalScoreMath/(num-1));

	return 0;
}