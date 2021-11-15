#include<stdio.h>
#include<stdlib.h>
#include<dirent.h>
#include<unistd.h>
#include<string.h>

int main() {

        char * cwd = (char *)malloc(sizeof(char) * 1024);
        DIR * dir = NULL;
        struct dirent * entry = NULL;

        getcwd(cwd, 1024);

        if( (dir = opendir(cwd)) == NULL) {
                printf("current directory error\n");
                exit(1);
        }

        int length = 0;
        while( (entry = readdir(dir)) != NULL) {
                if ((entry->d_name)[0]!='.'){
                        length = length + strlen(entry->d_name);
                        if (length > 20) {
                                printf("\n");
                                length = 0;
                        }
                        //printf("%d", length);
                        printf("%s\t", entry->d_name);
                }
        }
        printf("\n");

        free(cwd);
        closedir(dir);



        return 0;

}
