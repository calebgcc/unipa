# Given n matrices we want to find the order in wich the number of operations is minimum
from functools import lru_cache
import mimetypes

def minimum_number_operations(M):
    # memo will contain the best k for i,j
    memo =[[0]*len(M) for i in range(len(M))]
    # helper function
    @lru_cache(None)
    def dp(i,j):
        if i==j: return 0
        minimum = float('inf')
        for k in range(i,j):
            temp = minimum
            minimum = min(minimum, dp(i,k)+dp(k+1,j)+M[i][0]*M[k][1]*M[j][1])
            if temp != minimum: memo[i][j] = k
        return minimum
    return memo,dp(0,len(M)-1)

def best_ordering_multiplication(list_shapes):
    memo,number_of_operations = minimum_number_operations(list_shapes)
    M = [f'M{i}' for i in range(1,len(list_shapes)+1)]
    def help_backtracking(i,j):
        if i==j: return M[i]
        k = memo[i][j]
        a = help_backtracking(i,k)
        b = help_backtracking(k+1,j)
        return '('+a+')x('+b+')'
    return help_backtracking(0,len(M)-1),number_of_operations

if __name__ == '__main__':
    M = [(10,20),(20,50),(50,1),(1,100)] # 4 matrices of shapes (a,b)
    N = len(M)

    # what's the minimum possible number of operations?
    memo =[[0]*N for i in range(N)]
    for d in range(1,N):
        for i in range(0,N-d):
            j = d+i
            memo[i][j] = float('inf')
            for k in range(i,j):
                memo[i][j] = min(memo[i][j],memo[i][k]+memo[k+1][j]+M[i][0]*M[k][1]*M[j][1])

    print(memo[0][N-1])
    print(best_ordering_multiplication(M)[0])




   