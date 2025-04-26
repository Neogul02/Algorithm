n = int(input())  # 계단 개수
s = [int(input()) for _ in range(n)]

if n <= 2:  # 계단이 2개 이하일 때
    print(sum(s))
else:
    dp = [0] * n
    dp[0], dp[1] = s[0], s[0] + s[1] 
    for i in range(2, n): 
        dp[i] = max(dp[i - 3] + s[i - 1] + s[i], dp[i - 2] + s[i])
    print(dp[-1])