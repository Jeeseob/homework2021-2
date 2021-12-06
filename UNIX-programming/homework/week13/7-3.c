#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(void)
{
	int pd[2];
	char str[] = "Pipe test";
	char buf[BUFSIZ];
	int len, status;

	// 파이프 생성 중 오류발생시, 에러메세지 출력
	if(pipe(pd) == -1) {
		perror("pipe Error");
		exit(1);
	}
	

	// 파이프 write에 str 입력
	write(pd[1], str, strlen(str));
	printf("%d wirtes %s to the pipe.\n",(int)getpid(), str);
	
	// 파이프 read에서 데이터 읽어오기
	len = read(pd[0], buf, BUFSIZ);
	
	// 받아온 데이터 출력
	printf("%d reads %s from the pipe.\n", (int)getpid(), buf);
	
	// 파이프 닫기
	close(pd[0]);
	close(pd[1]);
	
	return 0;
}