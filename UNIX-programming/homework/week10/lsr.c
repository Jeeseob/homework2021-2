#include<stdio.h>
#include<stdlib.h>
#include<dirent.h>
#include<unistd.h>
#include<string.h>

extern int errno;

void lsRecursive(char * cwd);

int main(int argc, char *argv[]) {


        // 현재 경로를 입력받기 위한 변수(문자열)
        char cwd[1024] = ".";
        char * cwdFor = (char *)malloc(sizeof(char) * 1024);

        // 입력받은 경로가 없는 경우에는 현재 경로로 사용 
        if (argc == 1) {
                lsRecursive(cwd);
        }

        // 입력받은 경로 가 있는 경우 해당 경로를 cwd로 설정.
        else {
                for (int i = 1; i < argc ;i++){
                        strcpy(cwdFor,cwd);
                        strcat(cwdFor,"/");
                        strcat(cwdFor,argv[i]);
                        // 입력받은 인자들을 입력으로 반복 호출
                        lsRecursive(cwdFor);
                }
        }
        return 0;

}

void lsRecursive(char * cwd) {

        // 현재 디렉토리에 대한 정보
        DIR * dir = NULL;

        //각 파일별 정보
        struct dirent * entry = NULL;

        // 파일명 저장을 위한 문자열 배열 
        // 유닉스는 현재 대부분 최대 파일명을 255자로 제한하고 있어서 256으로 
        char * files[256];
        char * dirs[256];

        char * err;

        // 입력받은 디렉토리 열기c
        if( (dir = opendir(cwd)) == NULL) {
                // 디렉토리를 여는 중 문제 발생시 에러코드 출력
                err = strerror(errno);
                printf("ls: %s: %s\n", cwd+2, err);
                // 앞의 ./ 를 없애기 위해서 2byte 추가함.
                return;
        }

        //현재 디렉토리의 경로 출력하기
        printf("%s:\n",cwd);

        // 하위 파일 갯수 파악을 위한 변수
        int count = 0;

        // 입력받은 디렉토리의 하위 파일들 읽어와서 저장
        while((entry = readdir(dir)) != NULL) {
                // 숨겨진 파일, 현재 디렉토리, 이전 디렉토리 숨김
                if ((entry->d_name)[0]!='.'){

                        files[count] = entry->d_name;
                        count ++;

                }
                files[count] = NULL;
        }

        if(count == 0) {
                printf("\n");
                return;
        }

        // 하위파일들을 파일명 기준으로 정렬(bubble sort)
        char tmp[256];
        for(int i=0; i<count-1; i++ ) {
                for(int j=0; j<count-1-i; j++ ) {
                        if( strcmp( files[j], files[j+1]) > 0 ) {
                                strcpy( tmp, files[j] );
                                strcpy( files[j], files[j+1]);
                                strcpy( files[j+1], tmp );
                        }
                }
        }


        //현재 디렉토리의 하위 파일들 출력하기
        int length = 0;
        int i = 0;
        while(files[i]!= NULL) {
                length = length + 8;

                //현재까지 출력된 문자열의 길이가 일정 길이 이상인 경우, 개행문자로 줄 바꿈
                if (length > 76) {
                        printf("\n");
                        length = 0;
                }
                printf("%s\t", files[i]);
                i++;
        }
        printf("\n\n");


        // dir을 새로 열기 위하여 close
        closedir(dir);


        // 현재 디렉토리 다시 열
        if( (dir = opendir(cwd)) == NULL) {
                // 디렉토리를 여는 중 문제 발생시 에러코드 출력
                // 앞에서 열었는데, 이번에는 안열리는 경우라서, 에러코드를 다르게 설정하기 위해 임의로 에러코드를 출력하였습니다.
                printf("directory error\n");
                return;
        }

        // 하위 디렉토리 갯수 파악을 위한 변수
        int dirCount = 0;

        // 입력받은 디렉토리의 하위 파일들 읽어와서 저장
        while((entry = readdir(dir)) != NULL) {
                // 숨겨진 파일, 현재 디렉토리, 이전 디렉토리 숨김
                if ((entry->d_name)[0]!='.'){
                        if(entry->d_type == DT_DIR) {
                                dirs[dirCount] = entry->d_name;
                                dirCount++;
                        }
                }
        }

        // 하위디렉토리들을 파일명 기준으로 정렬(bubble sort)
        char dirTmp[256];
        for(int i=0; i<count-1; i++ ) {
                for(int j=0; j<dirCount-1-i; j++ ) {
                        if( strcmp( dirs[j], dirs[j+1]) > 0 ) {
                                strcpy( dirTmp, dirs[j] );
                                strcpy( dirs[j], dirs[j+1]);
                                strcpy( dirs[j+1], dirTmp );
                        }
                }
        }        

        char * cwdRecursive = (char *)malloc(sizeof(char) * 1024);

        // -R 옵션 구현 (재귀반복)
        for(int i=0; i<dirCount-1; i++ ) {
                // 현재 경로 지정;
                strcpy(cwdRecursive,cwd);
                strcat(cwdRecursive,"/");
                strcat(cwdRecursive,dirs[i]);
                //하위 디렉토리를 입력으로 재귀 호출
                lsRecursive(cwdRecursive);
        }
        // 동적할당 해제
        free(cwdRecursive);

}


