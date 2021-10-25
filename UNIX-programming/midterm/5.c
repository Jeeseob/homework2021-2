// header files
# include <sys/types.h>
# include <sys/stat.h>
# include <fcntl.h>
# include <unistd.h>
# include <stdlib.h>
# include <stdio.h>
# include <string.h>


#define SIZE 4096



int main(void) {
	int fdin, fdout;
	char buf[SIZE];

	// read/write를 위한 변수 추가
	int n;

	// open file dummy for read
	fdin = open("dummy", O_RDONLY);
	if (fdin == -1) {
		// 파일 오픈 관련 Error 처리
		perror("Open dummy");
		exit(1);
	}

	// open file copy for write
	// copy file이 없다면, 만든다 (O_CREAT)
	
	fdout = open("copy", O_CREAT | O_WRONLY, 0644);
	if (fdout == -1) {
		// 파일 오픈 관련 Error 처리
		perror("Open copy");
		exit(1);
	}
	// 파일이 끝날때 까지 dummy의 내용을 copy로 복사한다.
	// read dummy onto buf[] with SIZE
	while((n = read(fdin, buf, SIZE)) > 0) {
		// write buf[] to copy
		if (write(fdout, buf, n) != n) {
			perror("Write");
		}
	}
	// 완료 후 파일 닫기
	close(fdin);
	close(fdout);

	return 0;
}
