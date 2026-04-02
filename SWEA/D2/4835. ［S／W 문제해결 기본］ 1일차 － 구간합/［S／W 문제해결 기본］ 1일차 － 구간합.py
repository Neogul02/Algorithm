T = int(input())
for tc in range(1, T+1):
    N, M = map(int, input().split())
    arr = list(map(int, input().split()))
    
    sumArr = []
    
    for i in range(0, N-M+1):
        sumArr.append(sum(arr[i:M+i]))
        
    print(f'#{tc} {max(sumArr)-min(sumArr)}')