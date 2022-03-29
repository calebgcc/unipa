# Given n matrices we want to find the order in wich the number of operations is minimum
from functools import lru_cache

# TODO add backtracking

def ordering(a,b,M):
    @lru_cache(None)
    def dp(i,j):
        if i==j: return 0
        minimum = float('inf')
        for k in range(i,j):
            minimum = min(minimum, dp(i,k)+dp(k+1,j)+M[i][0]*M[k][1]*M[j][1])
        return minimum
    return dp(a,b)





if __name__ == '__main__':
    M = [(10,20),(20,50),(50,1),(1,100)] # 4 matrices of shapes (a,b)
    N = len(M)
    memo =[[0]*N for i in range(N)]
    # what's the minimum possible number of operations?
    for d in range(1,N):
        for i in range(0,N-d):
            j = d+i
            memo[i][j] = float('inf')
            for k in range(i,j):
                memo[i][j] = min(memo[i][j],memo[i][k]+memo[k+1][j]+M[i][0]*M[k][1]*M[j][1])
    print(memo[0][N-1])
    print(ordering(0,N-1,M))