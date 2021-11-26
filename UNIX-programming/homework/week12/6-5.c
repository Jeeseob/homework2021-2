#include <stdio.h>

int main(void)
{
	int i;
	// 10번 반복
	for(i = 0; i < 10; i++)
	{
		printf("sleep 1 second...\n");
		// 1초 대
		sleep(1);
	}	
	return 0;
}