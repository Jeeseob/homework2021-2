#include <sys/types.h>
#include <sys/times.h>
#include <limits.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>       

int main()
{
        struct tms tms;

        clock_t t1, t2;

         // 시작시간 t1으로 저장
        if ((t1 = times(&tms)) == -1) 
        {
                perror("times 1");
                exit(1);
        }       
  

        // 5-5.c 연산 실행
        int i;
        for(i = 0; i <= 1000000; i++)
                printf("%d\n", i);

        // 5-5.c 연산 종료

        // 종료 시간 t2로 저장
        if ((t2 = times(&tms)) == -1) 
        {
                perror("times 2");
                exit(1);
        }

        // 각각 time 출력
        printf("Real time : %.001f sec\n", (double)(t2 - t1) / sysconf(_SC_CLK_TCK));
        printf("User time : %.001f sec\n", (double)tms.tms_utime / sysconf(_SC_CLK_TCK));
        printf("System time : %.001f sec\n", (double)tms.tms_stime / sysconf(_SC_CLK_TCK));

        return 0;
}
