#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>

int main(void)
{
	struct stat buf;

	// password.txt에서 buf로 status 읽어오기
	stat("password.txt", &buf);
	
	// 변경 전 mode 출력
	printf("mode before change = %o\n", (unsigned int)buf.st_mode);

	// 받아온 buf의 mode값을 변경. 그룹 쓰기 권한 추가, 기타 사용자 읽기 권한 제거
	buf.st_mode |= S_IWGRP;
	buf.st_mode &= ~(S_IROTH);

	// 변경된 mode 값 적용
	chmod("password.txt",buf.st_mode);
	printf("mode after change = %o\n", (unsigned int)buf.st_mode);
	
	return 0;
}
