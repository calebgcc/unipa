#include <stdio.h>
#include <stdlib.h>
#define R 4
#define C 4

int path(int matrix[R][C],int i,int j);
int max(int a,int b);

char memo[R][C];
void main(){
	
	int matrix[R][C] = {{1,1,1,1},
						{2,11,7,7},
						{3,4,2,7},
						{4,6,5,6}};

	int sum = path(matrix,R-1,C-1);
	printf("%d\n",sum);

	
	int x=R-1,y=C-1;
	while(x!=0 || y!=0){
		printf("(%d,%d)=%d |",x,y,matrix[x][y]);
		memo[x][y]=='<' ? y--:x--;
	}
	printf("(%d,%d)=%d |",x,y,matrix[x][y]);
	printf("\n");
}

int max(int a, int b){
	return (a>b) ? a:b;
}

int path(int matrix[R][C],int i,int j){
	
	static int dp[R][C];

	if((i==0)&&(j==0))
		return matrix[0][0];

	if(dp[i][j-1]==0 && j!=0){
		dp[i][j-1]= path(matrix,i,j-1);
	}
		
	if(dp[i-1][j]==0 && i!=0){
		dp[i-1][j]= path(matrix,i-1,j);
	}
	
	if(i==0){	
		memo[i][j]='<';
		return dp[i][j-1]+matrix[i][j];
	}
	if(j==0){
		memo[i][j]='^';
		return dp[i-1][j]+matrix[i][j];
	}

	if(dp[i][j-1]>dp[i-1][j])
		memo[i][j]='<';
	else
		memo[i][j]='^';

	return max(dp[i][j-1]+matrix[i][j], dp[i-1][j]+matrix[i][j]);
}
