
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>
#include <stdio.h>


// 아무 동작이 없는 handler()
void handler(int signo) {}


int main(void) {

	struct sigaction act;

	sigemptyset(&act.sa_mask);
    act.sa_flags = 0;

    // signal이 들어왔을 때 핸들러 함수를 사용하도록 핸들러 변경
    act.sa_handler = handler;

    // sigaction 함수를 활용하여, Ctrl-c를 입력하는 경우, handler를 실행하도록 변경
    if (sigaction(SIGINT, &act, (struct sigaction *)NULL) < 0) {
        	perror("sigaction");
        	exit(1);
    }

	short i = 0;
	// 무한대로 동작하도록 코드가 구성되어 있다.
	while(1) {
		if(i<0)
		i = 0;
		printf("%d\r",i++);
	}

	return 0;
}
