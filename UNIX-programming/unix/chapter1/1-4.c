#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>
#include<errno.h>

int main(void) {
    char* fileName = "unix.txt";
    if (access(fileName ,R_OK) == -1) { // R_OK readable 한지 여부 확인.
        perror(fileName); // errno에 따른 error Message 출력
        // fileName: Error Message
        exit(1);
    }

    return 0;
}
