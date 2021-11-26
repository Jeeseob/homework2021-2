#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(void)
{	
	pid_t pid;
	
	for (int i = 0; i<3; i++) {
		if((pid = fork()) < 0)
		{
			//fork를 출력하고 종료
			perror("fork");
			exit(1);
		}

		// 그렇지않고 pid 가 양수이면 즉 부모 프로세스이면
		if(pid <= 0)
		{
			printf("my pid is %d ppid is %d\n", (int)getpid()
				, (int)getppid());
			
			return 0;
		}	
	}
}