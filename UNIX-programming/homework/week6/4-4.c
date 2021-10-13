#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdio.h>

int main(void) {
	struct stat buf;
	
	// password.txt에서 buf로 status 읽고, 출력
	stat("password.txt", &buf);
	printf("Link Count before link()= %d\n",(int)buf.st_nlink);	

	// password.ln 파일을 password.txt 파일과 link
	link("password.txt","password.ln");	

	// password.txt의 link 후 status 읽고, 출력
	stat("password.txt", &buf);
	printf("Link Count after link()= %d\n", (int)buf.st_nlink);
	
	// password.ln의 link 후 status 읽고, 출력
	stat("password.ln", &buf);
	printf("Link Count of password.ln= %d\n",(int)buf.st_nlink);

	// password.sln 파일을 password.txt 파일과 symbolic link
	symlink("password.txt", "password.sln");
	
	// password.txt의 link 후 status 읽고, 출력
	stat("password.txt", &buf);
	printf("Link Count of password.txt= %d\n", (int)buf.st_nlink);
	
	// password.txt의 link 후 status 읽고, 출력 (symbolic link로 인해 password.txt의 status가 출력됨)
	stat("password.sln", &buf);
	printf("Link Count of password.sln= %d\n", (int)buf.st_nlink);
	
	// password.sln 자체의 status 읽고 출력.
	lstat("password.sln", &buf);
	printf("Link Count of password.sln itself= %d\n", (int)buf.st_nlink);

	return 0;
}
