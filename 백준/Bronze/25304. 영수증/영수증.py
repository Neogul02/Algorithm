total_price = int(input())
total_cnt = int(input())

total_sum = 0
for _ in range(total_cnt):
    price, cnt = input().split()
    total_sum += int(price) * int(cnt)

print('Yes' if total_sum == total_price else 'No')