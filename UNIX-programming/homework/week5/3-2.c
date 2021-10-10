# include <sys/types.h>
# include <sys/stat.h>
# include <fcntl.h>
# include <unistd.h>
# include <stdlib.h>
# include <stdio.h>
// write 
# include <string.h>
int main(void) {

	int fd;
	int n;

	char str[] = "UNIX SYSTEM PROGRAMMING";
	// 파일을 쓰기 전용으로 연다.
	fd = open("3-1.txt", O_WRONLY);

	if (fd == -1) {
		perror("Open");
		exit(1);
	}
	// 파일에 str의 길이만큼 str을쓰기한다.
	n = write(fd, str, strlen(str));
	if (n != strlen(str)) {
		perror("Write");
		exit(2);
	}

	close(fd);

	return 0;
}
