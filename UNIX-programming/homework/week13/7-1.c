#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char *argv[]) 
{
    	int fd;
    	caddr_t addr;
    	struct stat statbuf;

	//command-line arguemnt가 없거나 argument에 문제가 있는 경우, 오류를 출력한다.
    	if (argc != 2) {
        	fprintf(stderr, "Usage : %s filename\n", argv[0]);
       		exit(1);
    	}
        //command-line arguemnt 대한 정보를 받아 올 수 없는경우, 오류를 출력한다.
    	if (stat(argv[1], &statbuf) == -1) {
        	perror("stat");
        	exit(1);
    	}
	
	// argument로 받은 data를 파일명으로 하는 파일을 열 수 없는 경우, 오류를 출력한다.
    	if ((fd = open(argv[1], O_RDWR)) == -1) {
        	perror("open");
        	exit(1);
    	}
	
	// 파일의 내용을 메모리에 매핑한다.
    	addr = mmap(NULL, statbuf.st_size, PROT_READ|PROT_WRITE, MAP_SHARED, fd, (off_t)0);
    
    // 메모리 매핑이 실패한 경우, 오류를 출력한다.
	if (addr == MAP_FAILED) {
        	perror("mmap");
        	exit(1);
    	}
    	close(fd);
	
	// addr(메모리 매핑에 대한 내용)을 출력한다.
    	printf("%s", addr);

    	return 0;
}