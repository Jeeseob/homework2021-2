#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>
#include<errno.h>
#include<string.h>


int main(void) {

    char* str;

    if (access("linux.txt" ,F_OK) == -1) { // F_OK 파일이 존재하는지 확인.
        str = strerror(errno); // errno에 따른 error Message를 str에 저장
        printf("linux.txt : %s\n",str ); // linux.txt: Error Message
        exit(1);
    }

    return 0;
}

