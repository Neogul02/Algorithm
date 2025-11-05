import sys

data = list(map(int, sys.stdin.read().split()))
nums = []
for x in data:
    if x == 0:
        break
    nums.append(x)

if not nums:
    sys.exit()

limit = max(nums) * 2
is_prime = [True] * (limit + 1)
is_prime[0] = is_prime[1] = False
for i in range(2, int(limit ** 0.5) + 1):
    if is_prime[i]:
        for j in range(i * i, limit + 1, i):
            is_prime[j] = False

prefix = [0] * (limit + 1)
cnt = 0
for i in range(limit + 1):
    if is_prime[i]:
        cnt += 1
    prefix[i] = cnt

out = [str(prefix[2 * n] - prefix[n]) for n in nums]
print("\n".join(out))
