#include<unistd.h>
#include<stdio.h>
#include<errno.h>

extern int errno; // extern은 외부 프로그램의 값을 받아온다.

int main(void) {

    // access(fileName, F_OK)는 파일이 존재하는지 여부를 판단한다.
    if (access("unix.txt", F_OK) == -1) {
        printf("errno=%d\n", errno); // error number
    }
    return 0;
}
