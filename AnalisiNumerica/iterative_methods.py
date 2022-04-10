import numpy as np


# given a matrix return True if is pdf for rows/columns False otherwise
def is_pdf(M, double_checking=True):
    # checking rows
    for i,row in enumerate(M):
        mii = abs(row[i])
        row_sum = sum(abs(m) for m in row)
        if mii <= (row_sum - mii):
            return is_pdf(M.T, False) if double_checking else False
    return True

def jacobi(A,b,K=None):
    N = A.shape[0]
    K = N**3 if K is None else K
    x = np.zeros(N) # solution
    t = np.zeros(N) # temp

    while K > 0: 
        for i in range(N):
            x[i] = 1/(A[i][i]) * (b[i] - (sum(A[i][j]*t[j] for j in range(N))-A[i][i]*t[i]))
        K -= 1
        t = np.array(x)

    return x

def gauss_seidel(A,b,K=None):
    N = A.shape[0]
    K = N**3 if K is None else K
    x = np.zeros(N) # solution
    
    while K > 0:
        for i in range(N):
            x[i] = 1/(A[i][i]) * (b[i] - (sum(A[i][j]*x[j] for j in range(N))-A[i][i]*x[i]))
        K -= 1
    
    return x

if __name__ == '__main__':
    
    # given Ax = b
    A = np.array([
        [4,1,0],
        [3,2,0],
        [0,0,1],
    ])

    b = np.array([6,7,5])

    print(is_pdf(A))
    x = jacobi(A,b)
    print(f'JACOBI :: {x=}')
    x = gauss_seidel(A,b)
    print(f'GAUSS-SEIDEL :: {x=}')
    print(f'{A@x.T=}')