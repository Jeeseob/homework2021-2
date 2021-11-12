#include<stdio.h>
#include<stdlib.h>
#include<dirent.h>
#include<unistd.h>
#include<string.h>

int main() {

        // 현재 경로를 입력받기 위한 변수(문자열)
        char * cwd = (char *)malloc(sizeof(char) * 1024);


        //입력받은 경로가 유의미한 경로인지 확인 + 에러시 에러메세지 출력

        // 입력받은 경로가 없는 경우에는 현재 경로로 사용 
        
        //현재 경로
        getcwd(cwd, 1024);

        //함수실행 (현재 경로를 입력으로)

        // 이후에 argument 입력받아서 처리하도록 변경
        lsRecursive(cwd);

        free(cwd);
        closedir(dir);

        return 0;

}

void lsRecursive(char * cwd) {

        // 현재 디렉토리에 대한 정보
        DIR * dir = NULL;

        //각 파일별 정보
        struct dirent * entry = NULL;

        // 파일명 저장을 위한 문자열 배열 파일명은 최대 256
        char * files[256];


        // 입력받은 디렉토리 열기
        if( (dir = opendir(cwd)) == NULL) {
                // 디렉토리를 여는 중 문제 발생시 에러코드 출력
                printf("current directory error\n");
                exit(1);
        }

        // 하위 파일 갯수 파악을 위한 변수
        int count = 0;

        // 입력받은 디렉토리의 하위 파일들 읽어와서 저장
        while((entry = readdir(dir)) != NULL) {
                // 숨겨진 파일, 현재 디렉토리, 이전 디렉토리 숨김
                if ((entry->d_name)[0]!='.'){
                       
                        // 이부분 
                        files = entry->d_name;
                        // 수정

                        count ++;
                }
        }

        // 하위파일들을 파일명 기준으로 정렬(bubble sort)
        char tmp[256];
        for( i=0; i<count-1; i++ ) {
                for( j=0; j<count-1-i; j++ ) {
                        if( strcmp( files[j], files[j+1]) > 0 ) {
                                strcpy( tmp, files[j] );
                                strcpy( files[j], files[j+1]);
                                strcpy( files[j+1], tmp );
                        }
                }
        }

        //현재 디렉토리의 경로 출력하기


        //현재 디렉토리의 하위 파일들 출력하기
        int length = 0;

        while(파일이 있는 동안) {
                length = length + strlen(entry->d_name);
                // 현재까지 출력된 문자열의 길이가 일정 길이 이상인 경우, 개행문자로 줄 바꿈
                if (length > 20) {
                        printf("\n");
                        length = 0;
                }
                //printf("%d", length);
                printf("%s\t", files[몇번째 인덱스 인지]);
        }
        // length 초기화
        length = 0;
        printf("\n");


        // -R 옵션 구현
        whie(현재디렉토리의 하위 디렉토리) {
                // 파일이 디렉토리인 경우 함수 실행
                if(디렉토리인 경우) {
                        //하위 디렉토리를 입력으로 재귀 호출
                        lsRecursive(디렉토리 경로);
                }
        }
}

// 파일명을 최대 256자로 임의로 설정하였다.
// 실제 유닉스의 최대 파일명이 몇 byte인지 파악 하면 좋을듯

// 출력시 정렬방식이 의문이다. 기본적으로 알파벳 순서 + 길이와 관련된 것 같은데 명확하지 않아 구현하지 못하였다.
// 단순 알파벳 순으로 구현

// 파일 속성에 따라 색상이 달라진다.
// cmd에서 글 색상이 다르게 출력하는 방법을 찾아봐야 겠다.

// Error 코드를 많이 작성하여, 문제 발생을 줄여야 하지만, 
// 아직 Error 코드를 적재적소에 넣는 것을 생각해내기가 힘들다. test는 여러번 진행해 봤지만, 예상치 못한 오류가 발생할 수 있을 것 같다.
