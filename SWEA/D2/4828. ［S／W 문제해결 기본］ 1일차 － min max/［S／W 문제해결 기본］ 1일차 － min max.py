T = int(input())
for tc in range(1, T+1):
    N = int(input())
    arr = list(map(int, input().split()))
    
    maxNum = max(arr)
    minNum = min(arr)
    
    print(f'#{tc} {maxNum-minNum}')