#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(void)
{
	int pd[2];
	pid_t pid;
	char str[] = "Pipe test";
	char buf[BUFSIZ];
	int len, status;


	// 파이프 생성 중 오류발생시, 에러메세지 출력
	if(pipe(pd) == -1) {
		perror("Pipe Error");
		exit(1);
	}

	// 파이프 write에 str 입력
	write(pd[1], str, strlen(str));
	printf("%d wirtes %s to the pipe.\n",(int)getpid(), str);

	
	switch(pid = fork()) {
		// fork 중 에러 발생시, 오류메세지 출력
		case -1:
			perror("fork Error");
			exit(1);
			break;

		// 자식 프로세스인 경우 실행
		case 0:
			// 파이프 read에서 데이터를 읽어오기
			len = read(pd[0], buf, BUFSIZ);
			printf("%d read '%s' from the pipe.\n", (int)getpid(), buf);
			break;			
	}

	// 파이프 닫기
	close(pd[0]);
	close(pd[1]);
	
	return 0;
}
