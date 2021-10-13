#include <dirent.h>
#include <stdlib.h>
#include <stdio.h>

int main(void)
{
	
	DIR* dp;
	
	struct dirent* dent;
	
	// 현재 디렉토리(.) 열기
	if((dp = opendir(".")) == NULL)
	{
		perror("opendir: .");
		exit(1);
	}
	
	// dir이 있는 동안 반복적으로 읽고, name과 inode 등 정보 출력
	while((dent = readdir(dp)))
	{	
		printf("Name: %s ", dent->d_name);
		printf("Inode: %d\n", (int)dent->d_ino);
	}
	closedir(dp);
	
	return 0;
}