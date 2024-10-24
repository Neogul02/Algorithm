N, M = map(int, input().split())
card = list(map(int, input().split()))
result = 0

for i in range(N):
    for j in range(i + 1, N):
        for k in range(j + 1, N):
            current_sum = card[i] + card[j] + card[k]
            if current_sum <= M:
                result = max(result, current_sum)

print(result)