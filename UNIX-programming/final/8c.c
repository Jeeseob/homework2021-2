#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#define PORTNUM 9000

int main(int argc, char *argv[]) 
{
    	int sd;
    	char buf[256];
    	struct sockaddr_in sin;


    	char message[256];

    	// 인자로 받은 데이터를 모아서 하나의 문자열로 만든다.(서버로 전송 및 처리를 수월하게 하기 위해서	)
    	sprintf(message,"%s", argv[1]);

    	for (int i = 2; i< argc; i++) {
    			strcat(message," ");
                strcat(message,argv[i]);
        } 

        //소켓을 생성한다.
    	if ((sd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        	perror("socket");
        	exit(1);
    	}

    	// 소켓의 IP 주소를 localhost 주소로 지정하고, 포트번호를 9000으로 한다.
    	memset((char *)&sin, '\0', sizeof(sin));
    	sin.sin_family = AF_INET;
    	sin.sin_port = htons(PORTNUM);
    	sin.sin_addr.s_addr = inet_addr("127.0.0.1");

    	if (connect(sd, (struct sockaddr *)&sin, sizeof(sin))) {
        	perror("connect");
        	exit(1);
    	}


    	// 만들어진 메세지 문자열을 buf에 옮기고, 서버로 전송
    	sprintf(buf, "%s", message);
    	if (send(sd, buf, strlen(buf) + 1, 0) == -1) {
	        perror("send");
	        exit(1);
		}


		// 서버에서 서버의 로직에 따라 가공된 문자열을 받아온다.
    	if (recv(sd, buf, sizeof(buf), 0) == -1) {
        	perror("recv");
        	exit(1);
    	}
    	close(sd);
    	
    	// 받아온 문자열 출력
    	printf("From Server : %s\n", buf);

    	return 0;
}