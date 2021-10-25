#include <time.h>
#include <stdio.h>

int main(void) {
	struct tm *tm;
	time_t t;

	//tm에 localtime함수의 구조체인 tm을 받아 저장
	time(&t);
	tm = localtime(&t);

	//요일을 문자로 표현하기 위해서 문자열을 배열로 저장
	char* wday[7] = {"일","월","화","수","목","금","토"};

	// 문제에 있는 형식에 맞춰 출력
	printf("%d. %d. %d. ",tm->tm_year + 1900, tm->tm_mon + 1, tm->tm_mday); 
	// 요일의 경우 0~6까지 int형으로 저장됨. 때문에, 문자열로 변환과정 필요
	printf("(%s) ",wday[tm->tm_wday]);
	printf("%d:%d:%d %s\n", tm->tm_hour, tm->tm_min, tm->tm_sec, tm->tm_zone);

	return 0;
}