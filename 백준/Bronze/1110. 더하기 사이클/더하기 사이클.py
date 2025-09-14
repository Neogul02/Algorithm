N = int(input())
first = N
cnt = 0

while True:
    x = N // 10
    y = N % 10
    digit_sum = x + y
    N = y * 10 + digit_sum % 10
    cnt += 1
    if N == first:
        break

print(cnt)