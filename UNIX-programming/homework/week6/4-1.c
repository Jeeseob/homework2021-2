#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>

int main(void) {
	struct stat buf;

	// password.txt에서 buf로 status 읽어오기
	stat("password.txt", &buf);

	// stat의 항목 print 
	printf("Inode = %d\n", (int)buf.st_ino);
	printf("Mdoe = %o\n", (unsigned int)buf.st_mode);
	printf("Nlink = %o\n", (int)buf.st_uid);
	printf("UID = %d\n", (int)buf.st_uid);
	printf("GID = %d\n", (int)buf.st_gid);
	printf("SIZE = %d\n", (int)buf.st_size);
	printf("Atime = %d\n", (int)buf.st_atime);
	printf("Mtime = %d\n", (int)buf.st_mtime);
	printf("Ctime = %d\n", (int)buf.st_ctime);
	printf("Blksize = %d\n", (int)buf.st_blksize);
	printf("Blocks = %d\n", (int)buf.st_blocks);
 
	return 0;
}
