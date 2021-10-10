#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv)
{
	//임시파일명 템플릿 향태
	char fname[] = "tempXXXXXX";
	char str[] = "test str";
	int fd;
	
	// 임시파일명 생성
	fd = mkstemp(fname);
	
	// 파일 생성
	write(fd, str, sizeof(str)-1);
	// 임시파일명으로 생성된 파일의 이름 출력
	printf("temp name is %s\n",fname);
	close(fd);

	return 0;
}