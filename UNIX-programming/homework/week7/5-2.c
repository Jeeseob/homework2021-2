#include <pwd.h>
#include <stdio.h>

int main(void) 
{
    // passwd 구조체 선언 
    struct passwd *pw;

    // gimjiseob이라는 user의 passwd 구조체를 받아온다.
    pw = getpwnam("gimjiseob");

    // 받아온 구조체의 내부 변수 값들을 출력한다.
    printf("UID : %d\n", (int)pw->pw_uid);
    printf("Name: %s\n", pw->pw_name);
    printf("Home Directory : %s\n", pw->pw_dir);
    printf("Shell : %s\n", pw->pw_shell);
    return 0;
}
