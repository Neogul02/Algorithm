def gcd(n, m):
    while m > 0:
        n, m = m, n % m
    return n

def lcm(n, m):
    return n * m // gcd(n, m)

N, M = map(int, input().split())

print(gcd(N, M))
print(lcm(N, M))