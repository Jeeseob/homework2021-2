#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(void)
{
	pid_t pid;
	char* a[3];
	
	if((pid = fork()) < 0)
	{
		//자식 프로세스 생성 중 오류 발생시 에러 메세지 출력 및 종료
		perror("fork");
		exit(1);
	}	

	// 부모 프로세스인 경우 실행
	if(pid > 0)
	{	
		// pid 출력
		printf("Parent %d executes.\n", (int)getpid());
	}
	// 자식 프로세스인 경우 실행
	else
	{
		// 문자열 배열인 a에 아래에 해당하는 명령어 삽입
		printf("Child %d executes.\n", (int)getpid());
		// 명령어
		a[0] = "ls";
		a[1] = "-a";

		// 명령어의 끝을 의미
		a[2] = NULL; 
		// 수행될 프로그램의 명령어(a)와 경로인 /bin/ls를 인자로 전달 
		if (execv("/bin/ls",a) == -1)
		{
			// 오류 발생시 에러메세지 출력 및 종
			perror("exec");
			exit(2);
		}

	}

	return 0;
}