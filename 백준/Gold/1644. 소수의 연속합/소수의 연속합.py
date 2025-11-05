import sys
import math

N = int(sys.stdin.readline().strip())
is_prime = [True] * (N + 1)
is_prime[1] = False

for i in range(2, int(math.sqrt(N)) + 1):
    if not is_prime[i]: continue
    for j in range(2 * i, N + 1, i):
        is_prime[j] = False

primes = [i for i in range(1, N + 1) if is_prime[i]]

cnt = 0

for i in range(len(primes)):
    running = 0
    for j in range(i, len(primes)):
        running += primes[j]
        if running > N:
            break
        if running == N:
            cnt += 1
print(cnt)