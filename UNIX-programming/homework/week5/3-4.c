# include <sys/types.h>
# include <fcntl.h>
# include <unistd.h>
# include <stdlib.h>
# include <stdio.h> 

int main(void) {
	int fd, n;
	off_t start, cur;
	char buf[256];

	// 파일을 읽기 전용으로 열기
	fd = open("3-1.txt", O_RDONLY);

	if( fd == -1 ) {
		perror("Open unix.txt");
		exit(1);
	}

	// 커서를 파일의 시작지점으로
	start = lseek(fd, 0, SEEK_CUR);

	// fd에서 최대 255byte를 buf로 읽어온다.
	n = read(fd, buf, 255);
	printf("Offset start = %d, Read Str=%s, n=%d\n", (int)start, buf, n);
	// 커서를 파일의 시작지점으로
	cur = lseek(fd, 0, SEEK_CUR);
	printf("Offset cur = %d\n", (int)cur);

	// 커서를 파일에서 7번째 바이트의 위치로
	start = lseek(fd, 7, SEEK_SET);
	n = read(fd, buf, 255);
	// 버퍼 마지막에 개행문자 추가로 출력시 보기 편하도록 함.
	buf[n] = '\0';
	printf("Offset start=%d, Read Str=%s\n", (int)start, buf);

	close(fd);

	return 0;
}
