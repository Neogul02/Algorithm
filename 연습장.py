T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for i in range(T):
    arr = map(int, input().split())
    odd_sum = 0
    for num in arr:
        if num % 2 != 0:
            odd_sum += num
    print(f'#{i+1} {odd_sum}')
