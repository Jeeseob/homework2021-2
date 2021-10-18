#include <stdio.h>


int g_x = 2;
int n = 5;

	int add(int x) {
		return x+n+g_x;
	}

	int fp(int n, int g(int x)) {
		if (n == 0 ) return 1;

		g_x--;
		printf("%d",g(g_x));
		return fp(g_x, g);
	}

	void fps() {
		int r = fp(g_x,add);
		printf("%d",r);
	}

int main(void) {


	fps();
	return 0;
}