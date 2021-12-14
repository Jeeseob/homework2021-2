// glib 를 사용하여 구현하였습니다. 
// 컴파일시에 gcc -o 6 6.c 'pkg-config glib-2.0 --cflags --libs'으로 컴파일 하여야 정상 실행 됩니다.

#include <stdlib.h>  
#include <stdio.h>  
#include <string.h>
#include <glib.h>



// pinrt 함수, 문제와 같이 보이게 하기 위해서 \n이 아니라 tap 단위로 구분
void print(gpointer data, gpointer userdata) {
        printf("%s\t", (char*)data);
}

// 정렬을 위해서 사용되는 compare 함수 
gint compare(gconstpointer a, gconstpointer b) {
        return (gint)strcmp(*(const char **)a, *(const char**)b);
}

// 역순 정렬을 위해서 사용되는 reverse_compare함수 
// compare 함수에서 strcmp에 들어가는 값의 순서를 바꿔준 형태로 구현 하였습니다.
gint reverse_compare(gconstpointer a, gconstpointer b) {
  return (gint)strcmp(*(const char **)b, *(const char**)a);
}

int main(int argc, char *argv[])  
{  
        int i;  
        
        printf("Queue Order\n");

        // glib 에서 제공하는 queue사용
        GQueue* queue = g_queue_new();

        // data를 queue로 입력
        for (i = 1; i< argc; i++) {
                g_queue_push_head(queue, argv[i]);
        }  

        // data를 queue에서 출력 queue는 FIFO형태이기 때문에 먼저 들어온 값부터 출력
        while (!g_queue_is_empty(queue)) {
                printf("%s\t", (char*)g_queue_pop_tail(queue));
        }
        printf("\n\n");


        printf("Stack Order\n");

        // data를 queue로 입력(pop하였기 때문에 다시 받아오기   )
        for (i = 1; i< argc; i++) {
                g_queue_push_head(queue, argv[i]);
        } 
        // data를 출력 Stack은 FILO 형태이기 때문에 나중에 들어온 값부터 출력
        while (!g_queue_is_empty(queue)) {
                printf("%s\t", (char*)g_queue_pop_head(queue));
        }

        printf("\n\n");

        GPtrArray* array = g_ptr_array_new();

        // 배열에 데이터 입
        for(i=1; i<argc; i++) {
                g_ptr_array_add(array,argv[i]);
        }

        printf("Alphabetical Order\n");

        // 배열에 들어있는 값을 compare함수를 기반으로 sort한다.
        g_ptr_array_sort(array,compare);

        // 배열에 있는 값을 하나씩 출력
        g_ptr_array_foreach(array,print,NULL);        
        printf("\n\n");


        printf("reverse Alphabetical Order\n");

        // 배열에 들어있는 값을 reverse_compare함수를 기반으로 sort한다.
        g_ptr_array_sort(array,reverse_compare);

        // 배열에 있는 값을 하나씩 출력
        g_ptr_array_foreach(array,print,NULL); 
        printf("\n");


        exit(0);  
}  


