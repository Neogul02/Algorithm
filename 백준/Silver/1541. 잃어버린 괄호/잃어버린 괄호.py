N = input().split('-')
result = sum(map(int, N[0].split('+')))

for i in N[1:]:
    result = result - sum(map(int, i.split('+')))

print(result)