T = int(input())

for i in range(T):
    arr = map(int, input().split())
    odd_sum = 0
    for num in arr:
        if num % 2 != 0:
            odd_sum += num
    print(f'#{i+1} {odd_sum}')
