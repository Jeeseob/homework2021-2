#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include <ctype.h>

#define PORTNUM 9000

int main(void) 
{	
		int count;
    	char buf[256];
    	struct sockaddr_in sin, cli;
    	int sd, ns, clientlen = sizeof(cli);

    	//소켓을 생성한다.
    	if ((sd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        	perror("socket");
        	exit(1);
    	}

    	//소켓의 IP 주소를 localhost 주소로 지정하고, 포트번호를 9000으로 한다.
    	memset((char *)&sin, '\0', sizeof(sin));
    	sin.sin_family = AF_INET;
    	sin.sin_port = htons(PORTNUM);
    	sin.sin_addr.s_addr = inet_addr("127.0.0.1");

    	if (bind(sd, (struct sockaddr *)&sin, sizeof(sin))) {
        	perror("bind");
        	exit(1);
    	}

    	if (listen(sd, 5)) {
        	perror("listen");
        	exit(1);
    	}

    while (1) 
	{
		// 클라이언트의 요청을 수락한다.
        if ((ns = accept(sd, (struct sockaddr *)&cli, &clientlen)) == -1) {
            perror("accept");
            exit(1);
        }
        // 클라이언트 요청을 받은 후, 자식 프로세스를 만들어 동작을 수행한다.
        switch (fork()) {

            case 0:
            		
            	// 클라이언트에서 buf로 데이터를 받아온다(여기서는 argument를 묶은 값이다.)
            	if (recv(ns, buf, sizeof(buf), 0) == -1) {
        			perror("recv");
        			exit(1);
    			}

    			// 대소문자를 변경하는 로직
    			count = 0;
               	while (buf[count] != '\0') {
       				if (isupper(buf[count])){
            			buf[count] = tolower(buf[count]);
       				}
        			else if (islower(buf[count])){
            			buf[count] = toupper(buf[count]);
       				}
        			count++;
    				}
    			// 대소문자가 변경된 buf를 클라이언트로 다시 보낸다.
		    	if (send(ns, buf, strlen(buf) + 1, 0) == -1) {
		        	perror("send");
		        	exit(1);
		    		}
		    	close(ns);
          
        }
    }	

    	return 0;
}