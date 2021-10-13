#include <sys/stat.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main(void)
{	
	// 디렉토리를 만든다, 이름은 linux, 권한은 755
	if(mkdir("linux", 0755) == -1)
	{
		perror("linux");
		exit(1);
	}
	// 디렉토리를 만든다, 이름은 programming, 권한은 644
	if(mkdir("programming", 0644) == -1)
	{
		perror("programming");
		exit(1);
	}
	
	return 0;
}