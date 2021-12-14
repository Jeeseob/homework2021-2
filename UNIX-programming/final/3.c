#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>

#include <errno.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

#include <unistd.h>


#define N 100000000



// 세마포어 공용체 정의
union semun {
    int val;
    struct semid_ds *buf;
    unsigned short *array;
};


// 세마포어 초기화를 위한 함수
int initsem(key_t semkey) 
{
    	union semun semunarg;
    	int status = 0, semid;

    	semid = semget(semkey, 1, IPC_CREAT | IPC_EXCL | 0600);
    	
    	// 리턴 값이 -1이고, errono 값이 EEXIST이면, 이미 조재하는 세마포어 식별자 라는 의미
    	if (semid == -1)  {
        	if (errno == EEXIST)
        		// 기존 식별자를 읽어 들어온다.
         		semid = semget(semkey, 1, 0);
    		}
    		else {
    			// 세마포어 값을 1로 초기화 한다.
        		semunarg.val = 1;
        		status = semctl(semid, 0, SETVAL, semunarg);
    	}

    	if (semid == -1 || status == -1) {
        	perror("initsem");
        	return (-1);
    	}

    	return semid;
}


// semaphare를 활용해서 lock을 걸어주는 함
int semlock(int semid) {
    	struct sembuf buf;

    	// sem_op값을 잠금 기능을 수행한다.
    	buf.sem_num = 0;
    	buf.sem_op = -1;
    	buf.sem_flg = SEM_UNDO;
    	
    	if (semop(semid, &buf, 1) == -1) {
        		perror("semlock failed");
        		exit(1);
    	}

    	return 0;
}

int semunlock(int semid) {
    	struct sembuf buf;

    	// sem_op 값을 1로 변경해서 잠금을 해제 한다.
    	buf.sem_num = 0;
    	buf.sem_op = 1;
    	buf.sem_flg = SEM_UNDO;

    	if (semop(semid, &buf, 1) == -1) {
        	perror("semunlock failed");
        	exit(1);
    	}
    	return 0;
}


unsigned int s = 0;

void *c(void *d) {
	
	int i;
	int semid;
    
    pid_t pid = getpid();

    if ((semid = initsem(1)) < 0)
        exit(1);

   	//잠금
    semlock(semid);	
    // s를 더하는 부분이기 때문에 잠금을 걸어야 한다.
	for (i = 0; i < N; i++) {
		s++;
	}
	// 잠금 해제
	semunlock(semid);

	return NULL;
	
}

int main(void) {

	pthread_t t[2];
	int r;

	// 새로운 쓰레드를 c함수를 실행하도록 만든다.
	r = pthread_create(&t[0], NULL, c, NULL);
	//에러 발생시 에러코드 출력
	if(r) {
		perror("Thread create: ");
		exit(1);
	}

	// 새로운 쓰레드를 c함수를 실행하도록 만든다.
	r = pthread_create(&t[1], NULL, c, NULL);
	
	//에러 발생시 에러코드 출력
	if(r) {
		perror("Thread create: ");
		exit(2);
	}

	pthread_join(t[0], NULL);
	pthread_join(t[1], NULL);
	printf("%u\n", s);

	return 0;
}