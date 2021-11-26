#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(void)
{
	pid_t pid;
	int i, status;
	
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
		printf("Parent %d waits child %d\n", (int)getpid(), (int)pid);
		// 자식 프로세스 종료시까지 대기
		wait(&status);
		// 자식 프로세스가 종료된 이후 아래 코드 수행(출력)
		printf("Child's exit status = %x\n", status);	
		printf("Child's exit status = %d\n", status >> 8);
	}

	// 자식프로세스인 경우 실행
	else
	{
		//pid 출력을 5번 반복
		for(i = 0; i < 5; i++)
		{
			printf("Child %d executes.\n", (int)getpid());
			sleep(1);
		}
		exit(3);
	}
	
	return 0;
}