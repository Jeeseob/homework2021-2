#include<stdio.h>
#include<stdlib.h>

// main 함수가 실행 전 매개변수를 입력 받는다.
int main(int argc, char *argv[]) {
  int i, sum = 0;

  for(i=1; i <argc; i++) // 입력받은 매개변수의 수 만큼 반복
    sum += atoi(argv[i]); // 매개변수로 입력받은 값(타입 char)을 int형으로 변형 후 sum에 더해줌.

  printf("%d\n", sum);
  return 0;
}
