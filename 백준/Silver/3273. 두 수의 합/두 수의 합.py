def solve():
    numbers.sort() # 배열 오름차순 정렬
    cnt = 0
    leftP = 0
    rightP = N - 1

    while leftP < rightP:
        sum = numbers[leftP] + numbers[rightP]

        if sum == X:
            cnt += 1
            leftP +=1
            rightP -= 1
        elif sum < X: 
            leftP += 1
        else: 
            rightP -= 1
    print(cnt)
            
if __name__ == "__main__":
    N = int(input())
    numbers = list(map(int, input().split()))
    X = int(input())  
    
    solve()
    