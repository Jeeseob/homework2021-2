#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main(void)
{
	char* fname;
	char fntmp[BUFSIZ];
	char template[32];
	
	// 임시파일명 생성 및 fnamee으로 저장
	fname = tmpnam(NULL);
	printf("1.TMP File Name(tmpnam) : %s\n", fname);
	
	//임시파일명 생성(BUFSIZ)
	tmpnam(fntmp);
	printf("2. TMP File Name(tmpnam) : %s\n", fntmp);
	
	// 디렉토리 지정
	fname = tempnam("/tmp", "coredump");
	printf("3.TMP File Name(tempnam) : %s\n", fname);
	
	//템플릿을 지정하여 임시파일명 생성
	strcpy(template, "/tmp/coredumpXXXXXX");
	fname = mktemp(template);
	printf("4. TMP File Name(mktemp) : %s\n", fname);

	return 0;
}
