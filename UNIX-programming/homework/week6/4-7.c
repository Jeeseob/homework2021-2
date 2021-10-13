#include <sys/stat.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main(void)
{
	// 디렉토리 삭제
	if(rmdir("programming") == -1)
	{
		perror("programming");
		exit(1);
	}
	return 0;
}