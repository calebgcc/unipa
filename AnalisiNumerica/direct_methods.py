# direct methods for calculating LU factorization     
import numpy as np


def calc_u(i,j,A,LU):
    return A[i][j] - sum(LU[i][k]*LU[k][j] for k in range(0,i))

def calc_l(i,j,A,LU):
    return (1/LU[j][j])*(A[i][j]-sum(LU[i][k]*LU[k][j] for k in range(0,j)))

def bonachiewicz(A,LU):
    N = A.shape[0]
    for i in range(0,N):

        for j in range(0,i):
            LU[i][j] = calc_l(i,j,A,LU)

        for j in range(i,N):
            LU[i][j] = calc_u(i,j,A,LU)

def crout(A,LU):
    N = A.shape[0]
    for i in range(0,N):

        for j in range(i,N):
            LU[i][j] = calc_u(i,j,A,LU)

        for j in range(i+1,N):
            LU[j][i] = calc_l(j,i,A,LU)
        


if __name__ == '__main__':
    N = 5
    A = np.random.rand(N,N)
    LU = np.identity(N)

    #bonachiewicz(A,LU)
    crout(A,LU)

    print(A,end='\n\n')
    print(LU,end='\n\n')
    L = np.identity(N)
    U = np.identity(N)

    for i in range(0,N):
        for j in range(0,i):
            L[i][j] = LU[i][j]

    for i in range(0,N):
        for j in range(i,N):
            U[i][j] = LU[i][j]

    print(L,end='\n\n')
    print(U,end='\n\n')
    print(L@U,end='\n\n')


    