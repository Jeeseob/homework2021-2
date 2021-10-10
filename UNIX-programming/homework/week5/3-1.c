# include <sys/types.h>
# include <sys/stat.h>
# include <fcntl.h>
# include <unistd.h>
# include <stdio.h>
# include <stdlib.h>

int main(void) {

	int fd;
	// 파일이 없으면, 파일 생성 권한은 664		
	fd = open("3-1.txt", O_CREAT, 0644);

	// error 발생시, 
	if (fd == -1) {
		perror("Open");
	}
	
	printf("fd is %d\n", fd);
	
	// 파일 닫기
	close(fd);

	return 0;
}
