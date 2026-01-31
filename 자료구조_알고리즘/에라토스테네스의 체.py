# O(N log (log N)) 으로 선형시간에 가까운 소수 판별임
import math

N = 120
is_prime = [True] * (N + 1)  # 처음에는 모두 true로 초기화
is_prime[1] = False  # 1은 소수가 아니므로

# 에라토스테네스의 체 알고리즘
# 자연수 N까지라고 하면 2<= i <= sqrt(N) (루트) 까지만 확인하면 됨
for i in range(2, int(math.sqrt(N)) + 1):
    if not is_prime[i]: continue
    for j in range(2 * i, N + 1, i):
        is_prime[j] = False

for i in range(1, N + 1):
    print(i, is_prime[i])