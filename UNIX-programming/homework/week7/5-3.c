#include <time.h>
#include <stdio.h>

int main(void) {
	struct tm *tm;
	time_t t;

	// tm에 system 구조체인 tm을 받아와서 저장.
	time(&t);
	tm = localtime(&t);

	//요일을 문자로 표현하기 위해서 배열에 저장.
	char wday[7][3] = {"월","화","수","목","금","토","일"};


	printf("과제 내용에 맞춘 형식\n");
	// 과제에 있는 형식 2019. 10. 15. (화) 11:16:05 KST 
	printf("%d. %d. %d. ",tm->tm_year + 1900, tm->tm_mon + 1, tm->tm_mday); 
	// 요일의 경우 0~6까지 int형으로 저장되기 때문에, 문자열로 변환과정 필요
	printf("(%s) ",wday[tm->tm_wday]);
	printf("%d:%d:%d %s\n", tm->tm_hour, tm->tm_min, tm->tm_sec, tm->tm_zone);


	printf("현재 시스템 형식\n");
	// 현재 내 pc의 형식 2021년 10월 16일 토요일 16시 26분 53초 KST
	printf("%d년 %d월 %d일 ",tm->tm_year + 1900, tm->tm_mon + 1, tm->tm_mday); 
	// 요일의 경우 0~6까지 int형으로 저장되기 때문에, 문자열로 변환과정 필요
	printf("%s요일 ",wday[tm->tm_wday]);
	printf("%d시 %d분 %d초 %s\n", tm->tm_hour, tm->tm_min, tm->tm_sec, tm->tm_zone);

	return 0;
}
