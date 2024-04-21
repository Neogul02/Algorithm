T = int(input())

li = []
sum = 0
for i in range(0, T):
    a, b = map(int, input().split())
    sum = a+b
    li.append(sum)

for j in range(1,T+1):
    print(f"Case #{j}: {li[j-1]}")
