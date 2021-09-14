#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int main(void) {

  char *str;
  //str = malloc(sizeof(char)*20); //str에 동적할당 20 * char type의 크기(1byte)
  strcpy(str, "hello"); // "hello"를 str의 리스트에 넣는다.
  printf("%s\n",str);

  strcpy(str, "Good morning");
  printf("%s\n", str);

  free(str); // 동적 할당되어 있던 메모리를 할당을 푼다. 
  return 0;
}


