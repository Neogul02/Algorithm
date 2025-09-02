n = int(input())
days = [int(input().split('-')[1]) for _ in range(n)]
print(sum(1 for day in days if day <= 90))