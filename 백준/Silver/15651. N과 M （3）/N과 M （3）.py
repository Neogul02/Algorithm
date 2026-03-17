N, M = map(int, input().split())

selected = [False] * N;

def permutation_repeat(depth: int):
    # 기저조건
    if(depth ==M):
        for i in range(M):
            print(selected[i], end=' ')
        print()
        return
    
    # 수행로직
    for i in range(N):
        selected[depth] = i+1
        permutation_repeat(depth+1)

permutation_repeat(0)