

#include <stdio.h>
#include <stdlib.h>
#include "declaraciones.h"


int main(void) {


	int m [tamanyoMatrix][tamanyoMatrix] = {{1,3,3,0},{2,0,0,3},{4,0,1,2},{1,1,1,2}};

    int i;
	int j;

	puts("La matriz m es:");
	 for ( i = 0; i < tamanyoMatrix; i++ ) {
	      for ( j = 0; j <tamanyoMatrix; j++ ) {
	         printf("a[%d][%d] = %d\n", i,j, m[i][j] );
	      }
	 }

	 int res = equisuma(m);
	 printf("La suma de los elementos de la matriz es %d",res);


	 return 0;
}

int equisuma(int m[tamanyoMatrix][tamanyoMatrix]){
	return equisumaRecursiva(m,0,0,tamanyoMatrix);   // <-- Importante cambiar valor aqui
}

int equisumaRecursiva(int m[tamanyoMatrix][tamanyoMatrix], int i, int j, int n){

	int res = -1;
		int a=0;
		int b=0;
		int c=0;
		int d=0;

		if(n==2){
			res = m[i][j] + m[i][j+1] + m[i+1][j] + m[i+1][j+1];
		}else if(n>2){

			a = equisumaRecursiva(m,i,j,n/2);
			b = equisumaRecursiva(m,i,j+(n/2),n/2);
			c = equisumaRecursiva(m,i+(n/2),j,n/2);
			d = equisumaRecursiva(m,i+(n/2),j+(n/2),n/2);

			if(a==b && a==c && a==d && a!= -1){
				res = a+b+c+d;
			}
		}


		return res;
}


