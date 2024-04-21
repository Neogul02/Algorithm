T = int(input())

li = []
sum = 0
for i in range(0, T):
    a, b = map(int, input().split())
    li.append(a+b)
    print(f"Case #{i+1}: {a} + {b} = {li[i]}")
