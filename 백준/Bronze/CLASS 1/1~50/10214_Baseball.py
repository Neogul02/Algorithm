T = int(input())
for _ in range(T):
    Y = K = 0 
    for _ in range(9):
        y, k = map(int, input().split())
        Y += y
        K += k  
    print('Yonsei' if Y > K else 'Korea' if Y < K else 'Draw')
