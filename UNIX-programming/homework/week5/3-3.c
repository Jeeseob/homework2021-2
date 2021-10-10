# include <sys/types.h>
# include <sys/stat.h>
# include <fcntl.h>
# include <unistd.h>
# include <stdlib.h>
# include <stdio.h>
# include <string.h>
int main(void) {

	int readFd;
	int writeFd;
	int n;
	char buf[10];

	// 파일을 읽기 전용으로 연다.
	readFd = open("3-1.txt", O_RDONLY);
	if (readFd == -1) {
		perror("Open readFd");
		exit(1);
	}
	// 파일을 쓰기 전용으로 연다
	writeFd = open("3-3.txt", O_CREAT | O_WRONLY, 0644);
	if (writeFd == -1) {
		perror("Open wirteFd");
		exit(1);
	}

	// 파일이 끝날때 까지 readFd의 파일을 writeFd로 복사한다.(buf 활용 10byte씩)
	while((n = read(readFd, buf, 10)) > 0) {
		if (write(writeFd, buf, n) != n) {
			perror("Write");
		}
	}

	if (n == -1 ) {
		perror("Read");
	}

	close(readFd);
	close(writeFd);

	return 0;
}
