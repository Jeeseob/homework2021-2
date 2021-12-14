#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>


int main(void) 
{
    	int shmid, i;
    	char *shmaddr, *shmaddr2;
    	
    	char* message;
		message = "Message from ";
		//int lenght = strlen(message);
		char num[10];


    	// 키를 IPC_PRIVATE로 지정하여, 20바이트 공유 메모리를 생성한다.
    	shmid = shmget(IPC_PRIVATE, 20, IPC_CREAT|0644);
    	if (shmid == -1) {
        	perror("shmget");
        	exit(1);
    	}

    	// 자식 프로세스를 만든다.
    	switch (fork()) {
        	case -1:
            		perror("fork");
            		exit(1);
            		break;
        	
           	// 자식프로세스인 경우
        	case 0:
        			//공유 메모리 설정
            		shmaddr = (char *)shmat(shmid, (char *)NULL, 0);
            		printf("=Child begins\n");


            		//pid를 받아서, 문자열로 변경
					sprintf(num, "%d", getpid());

            		
            		//처음 메세지로 지정된 문자열 보내기
            		for (i=0; i<strlen(message); i++)
	               		shmaddr[i] = message[i];

	               	// pid로 만든 문자열을 뒤에 붙여서 보낸다.	
	                for (; i<strlen(message)+strlen(num); i++)
	               		shmaddr[i] = num[i-strlen(message)];
	               
	               	// 공유 메모리 닫기	
            		shmdt((char *)shmaddr);

            		printf("=Child ends\n");

            		exit(0);
            		break;
        	

        	default:
            		wait(0);
            		shmaddr2 = (char *)shmat(shmid, (char *)NULL, 0);

            		printf("=Parent begins\n");

            		// 데이터를 받아서 출력한다. 문자열의 마지막은 \0이기 때문에 \0가 나올때 까지 받음
            		// pid의 길이가 가변적이기 때문에	
            		i = 0;
            		while(shmaddr2[i] != '\0') {
                 		printf("%c", shmaddr2[i]);
                 		i++;	
            		}

                 	printf("\n");
                 	// 공유 메모리 닫기
            		shmdt((char *)shmaddr2);
            		shmctl(shmid, IPC_RMID, (struct shmid_ds *)NULL);

            		printf("=Parent ends\n");

            		break;
    	} 

    	return 0;
}
