#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>



int main(void)
{

	// SIGINT(ctrl + c)입력시 SIG_IGN(시그널 무시)로 변경하여 처
	if(sigset(SIGINT, SIG_IGN) == SIG_ERR)
		{
			// 오류 발생시 에러 메세지 출력
			perror("sigset");
			exit(1);
		}

	int i;
	// 10번 반복
	for(i = 0; i < 10; i++)
	{
		printf("sleep 1 second...\n");
		// 1초 대기 (입력을 받기 위해서)
		sleep(1);
	}	
	return 0;
}