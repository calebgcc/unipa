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

def jacobi(A,b,K=9):
    N = A.shape[0]
    x = np.zeros(N) # solution
    t = np.zeros(N) # temp

    while K > 0: 
        for i in range(N):
            S = 0
            for j in range(N):
                if i == j: continue
                S += A[i][j]*t[j]
            x[i] = 1/(A[i][i]) * (b[i] - S)
        K -= 1
        t = np.array(x)

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
    print(f'{x.T=}')
    print(f'{A@x.T=}')