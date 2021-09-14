#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>

int main(void) {
    //char* fileName = "linux.txt";
    if (access("linux.txt" ,R_OK) == -1) { // R_OK readable 한지 여부 확인.
        perror("linux.txt"); // errno에 따른 error Message 출력
        // fileName: Error Message
        exit(1);
    }

    return 0;
}


