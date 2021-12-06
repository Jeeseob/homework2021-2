#include <sys/types.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>


int main(int argc, char *argv[]) {

    int fd;
    caddr_t addr;
    struct stat statbuf;

    pid_t pid;

    //command-line arguemnt가 없거나 argument에 문제가 있는 경우, 오류메세지 출력
    if (argc != 2) {
        fprintf(stderr, "Usage : %s filename\n", argv[0]);
        exit(1);
    }
    
    //command-line arguemnt 대한 정보를 받아 올 수 없는경우, 오류메세지 출력
    if (stat(argv[1], &statbuf) == -1) {
        perror("stat");
        exit(1);
    }
    
    // argument로 받은 data를 파일명으로 하는 파일을 열 수 없는 경우, 오류메세지 출력
    if ((fd = open(argv[1], O_RDWR)) == -1) {
        perror("open");
        exit(1);
    }
    
    // 부모프로세스에서 파일 매  
    addr = mmap(NULL,statbuf.st_size, PROT_READ,MAP_SHARED,fd ,(off_t)0);

    switch (pid = fork()) {

    // fork가 실패한 경우, 오류메세지 출력
        case -1 :
            perror("fork");
            exit(1);
            break;
    
    // 자식프로세스인 경우,
        case 0 :
            // map의 내용을 stdout으로 출력
            write(1,addr,strlen(addr));
        }
    close(fd);
    return 0;

}