#include <unistd.h>
#include <stdio.h>

int main(void)
{	
	// sysconf 함수를 통해 정의된 상수를 활용하여 설정된 자원 값 검색.
	printf("_SC_ARG_MAX(1) : %ld\n",sysconf(_SC_ARG_MAX));
	printf("_SC_CHILD_MAX(2) : %ld\n",sysconf(_SC_CHILD_MAX));
	printf("_SC_CLK_TCK(3) : %ld\n", sysconf(_SC_CLK_TCK));
	printf("_SC_OPEN_MAX(5) : %ld\n",sysconf(_SC_OPEN_MAX));
	printf("_SC_VERSION(8) : %ld\n", sysconf(_SC_VERSION));
	return 0;
}
