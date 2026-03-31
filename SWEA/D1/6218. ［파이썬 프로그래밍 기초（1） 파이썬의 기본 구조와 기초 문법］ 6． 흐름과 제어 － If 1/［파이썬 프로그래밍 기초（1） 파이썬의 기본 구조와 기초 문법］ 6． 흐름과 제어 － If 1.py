N = int(input())

for i in range(1, N+1):
    for j in range(1, N+1):
        if(i * j == N):
            print(f'{i}(은)는 {N}의 약수입니다.')
            break