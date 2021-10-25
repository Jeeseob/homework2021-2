#include <stdio.h>
#include<unistd.h>
#include <time.h>

int main(void) {
	struct tm *tm;
	time_t t;
	char * cwd;
	char a;

	//요일을 문자로 표현하기 위해서 배열에 저장
	char* wday[7] = {"일", "월","화","수","목","금","토"};
	int n = 0;

	// 현재 디렉토리 위치를 받아서 cwd에 저장
	cwd = getcwd(NULL, BUFSIZ);

	// tm에 system 구조체인 tm을 받아와서 저장.
	time(&t);
	tm = localtime(&t);

	while(1) {
		printf("1) pwd\n2) date\n3) quit\n");	
		n = 0;	
		// scanf로 데이터를 받는 경우, 문자열 입력시 오류가 생겼음.
		// 버퍼를 활용하여 데이터를 받는 것으로 문제 해결
		a = getchar(); 
		// char형 1,2,3 은 각각 49,50,51의 값을 가지기 때문에 -48을 적용
		n = (int)a-48;

		// 입력 버퍼에 남은 값 지우기
		fflush(stdin);


		if (n == 1) {
			// 현재 디렉토리 위치 출력
			printf("%s\n",cwd);
		}

		else if (n ==2) {
			// 현재 시간값 출력
			// 문제에 있는 형식 2019. 10. 15. (화) 11:16:05 KST 
			printf("%d. %d. %d. ",tm->tm_year + 1900, tm->tm_mon + 1, tm->tm_mday); 
			// 요일의 경우 0~6까지 int형으로 저장되기 때문에, 문자열로 변환과정 필요
			printf("(%s) ",wday[tm->tm_wday]);
			printf("%d:%d:%d %s\n", tm->tm_hour, tm->tm_min, tm->tm_sec, tm->tm_zone);
		}

		else if (n==3) {	
			// 반복문 탈출 => 프로그램 종료
			break;
		}
		
		else {
			// 잘못된 입력 처리
			printf("Invalid selection\n");
		}
	}
	return 0;
}
