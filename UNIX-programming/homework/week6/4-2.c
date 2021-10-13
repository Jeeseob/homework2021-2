#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>

int main(void) {
	struct stat buf;

	// password.txt에서 buf로 status 읽어오기
	stat("password.txt", &buf);

	// 읽어온 status 중 st_mode를 각각 8진수와 16진수로 print
	printf("Mode = %o (16진수 : %x)\n", (unsigned int)buf.st_mode, (unsigned int)buf.st_mode);

	//password.txt의 파일 종류 찾기 
	if(S_ISFIFO(buf.st_mode))
		printf("password.txt: FIFO\n");
	if(S_ISDIR(buf.st_mode))
		printf("password.txt: Diretiry");
	if(S_ISREG(buf.st_mode))
		printf("password.txt: Regular File\n");
	
	return 0;
}
