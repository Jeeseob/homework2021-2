# include <stdlib.h>
# include <stdio.h>

int main(void) {

	FILE* readFd;
	FILE* writeFd;

	char c;
	
	// 파일 읽기
	if ((readFd = fopen("3-1.txt", "r")) == NULL) {
		perror("Open readFd");
		exit(1);
	}

	if((writeFd = fopen("3-5.txt", "w")) == NULL) {
		perror("Open writeFd");
		exit(1);
	}

	// char 단위로 EOF까지 읽어서 쓰기
	while((c = fgetc(readFd))!= EOF) {
		fputc(c,writeFd);
	}

	fclose(readFd);
	fclose(writeFd);

	return 0;
}
