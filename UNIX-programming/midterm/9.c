#include<sys/types.h>
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>
#include<wait.h>

int mian(void) {
	pid_t p;
	char *a[3];
	int s;

	switch (p = fork()) {
		case -1:
			perror("fork");
			exit(1);
			break;
		case 0:
			printf("Child %d excuted. \n",(int)getpid());
			a[0] = "ls";
			a[1] = "-a";
			a[2] = NULL;
			if(execv("/bin/ls",a) == -1 ) {
				perror("exec");
				exit(3);
			}
		default:
			printf("Parent %d is waiting for child's exit.\n", (int)getpid());
			wait(&s);
	}
	printf("Child gave exit status %d to paarent.\n", s>>8);

	return 0;
}