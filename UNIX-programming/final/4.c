#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main(void) 
{
    	int pd[2];
    	pid_t pid;

    	// 파이프 생성
    	if (pipe(pd) == -1) {
        	perror("pipe");
        	exit(1);
    	}

    	// fork
    	switch (pid = fork()) {
        
        case -1 :
            perror("fork");
            exit(1);
            break;

        // 자식일 때 실행
        case 0 : 
            // 사용하지 않는 파이프 닫기
            close(pd[0]);
        	// 파이프를 사용하여 표준 출력으로 복사한다.
            if (pd[1] != 1) {
                dup2(pd[1], 1);
               	close(pd[1]);
            }	
            // ls -l을 실행
            execlp("ls", "ls", "-l", (char *)NULL);
            exit(1);
            break;

        // 부모일 때 실행
        default : 
        	// 사용하지 않는 파이프를 닫는다.
       		close(pd[1]);
       		//파이프를 통해 데이터를 읽어들인다
            if (pd[0] != 0) {
                dup2(pd[0], 0);
           		close(pd[0]);
            }
            // grep c 를 실행
           	execlp("grep", "grep", "c", (char *)NULL);
            wait(NULL);
            break;
    	}

    	return 0;
}