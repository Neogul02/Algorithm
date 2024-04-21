def solution(n):
    return [i for i in range(1, n+1) if n % i == 0]

print(solution(24))  # [1, 2, 3, 4, 6, 8, 12, 24]