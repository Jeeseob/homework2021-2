#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(void)
{	
	pid_t pid;
	
	if((pid = fork()) < 0) {
		//자식 프로세스 생성 중 오류 발생시 에러 메세지 출력 및 종료
		perror("fork");
		exit(1);
	}

	// 부모 프로세스일때 실행
	if(pid > 0) {
		sleep(2);
		// 자신의pid와 자신의 ppid를 출력(parent).
		printf("Parent process has pid = %d ppid = %d\n", (int)getpid()
		, (int)getppid());
	}
	// 자식 프로세스인 경우 실행
	else
		// 자신의 pid와 ppid 출력(child)
		printf("Child process has pid = %d ppid = %d\n", (int)getpid()
		, (int)getppid());

	//종료 전 출력
	printf("End of fork ...\n");
	
	return 0;
}