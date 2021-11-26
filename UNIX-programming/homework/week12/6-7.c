#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

void handler(int signo)
{
	// Signal 입력시 실행 (signal 관련 정보 출력)
	psignal(signo, "Received Signal:");
	sleep(5);
	printf("In Signal Handler, After Sleep\n");
}

int main(void)
{
	// signal 집합과 관련된 구조체
	struct sigaction act;

	sigemptyset(&act.sa_mask);
	act.sa_flags = 0;
	// signal 처리 handler를 hnadler 함수로 지정
	act.sa_handler = handler;

	if(sigaction(SIGINT, &act, (struct sigaction *)NULL)  < 0)
	{
		// 오류 발생시 에러 메세지 출력
		perror("sigaction");
		exit(1);
	}
	
	// 반복
	while(1)
	{
		fprintf(stderr, "Input SIGINT: ");
		// 입력 대기(ctrl + c 입력시 handler 실행)
		pause();
		fprintf(stderr, "After Signal Handler\n");
	}
	return 0;
}