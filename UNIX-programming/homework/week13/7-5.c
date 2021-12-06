#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(void)
{
	int pd1[2];
	int pd2[2];
	pid_t pid;
	char buf[BUFSIZ];
	
	int len, status;

	char* str1;
	char* str2;

	str1 = "From child";
	str2 = "From parent";

	// 파이프 생성 중 오류발생시, 에러메세지 출력
	if(pipe(pd1) == -1) {
		perror("Pipe Error");
		exit(1);
	}

	if(pipe(pd2) == -1) {
		// 오류가 있다면 출력하고 종료
		perror("Pipe Error");
		exit(1);
	}
	
	switch(pid = fork()) {
		// fork 중 에러 발생시, 오류메세지 출력
		case -1:
			perror("fork Error");
			exit(1);
			break;

		// 자식 프로세스인 경우 동작
		case 0:
			// 자식 프로세스가  사용하지 않는, 파이프 닫기
			close(pd1[1]);
			close(pd2[0]);

			// 파이프 write에 데이터 입력
			write(pd2[1], str1, strlen(str1));

			// 파이프 read에서 데이터 읽어오기
			len = read(pd1[0], buf, BUFSIZ);
			printf("%d child read '%s'\n", (int)getpid(), buf);

			close(pd1[0]);
			close(pd2[1]);
			break;

		// 부모프로세스인 경우 동작
		default:
			// 부모 프로세스가  사용하지 않는, 파이프 닫기
			close(pd1[0]);
			close(pd2[1]);

			// 파이프 write에 데이터 입력
			write(pd1[1], str2, strlen(str2));

			// 파이프 read에서 데이터 읽어오기
			len = read(pd2[0], buf,BUFSIZ);
			printf("%d parent read '%s'\n", (int)getpid(), buf);

			close(pd1[1]);
			close(pd2[0]);
			break;
	}	
	return 0;
}