n = int(input())
p = list(map(int, input().split()))
p.sort()

total = 0
current = 0
for time in p:
    current += time
    total += current

print(total)