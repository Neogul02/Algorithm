T = int(input())

for i in range(T):
    result = ''
    n = int(input())

    result += '++++ ' * (n // 5)
    n = n % 5
    result += '|' * n

    print(result)