#include <unistd.h>
#include <stdio.h>

int main (void) {

	// 현재 프로세스의 pid
	printf("PID : %d\n", (int)getpid());
	// 부모 프로세스의 pid 
	printf("PPID : %d\n", (int)getppid());
	
	return 0;
	
}

