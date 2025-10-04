import math

n = int(input())

# 1개 체크
sqrt_n = int(math.sqrt(n))
if sqrt_n * sqrt_n == n:
    print(1)
    exit()

# 4개: 4^a * (8b + 7)
temp = n
while temp % 4 == 0:
    temp //= 4
if temp % 8 == 7:
    print(4)
    exit()

# 2개 체크
for i in range(1, int(math.sqrt(n)) + 1):
    remaining = n - i * i
    sqrt_remaining = int(math.sqrt(remaining))
    if sqrt_remaining * sqrt_remaining == remaining:
        print(2)
        exit()

# 나머지는 모두 3개
print(3)