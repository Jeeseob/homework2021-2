#include <sys/stat.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main(void)
{	
	// 디렉토리 이름 변경
		if( rename("linux", "LINUX")== -1)
	{
		perror("Linux");
		exit(1);
	}
	return 0;
}