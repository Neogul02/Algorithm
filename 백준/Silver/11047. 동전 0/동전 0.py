import sys
input = lambda: sys.stdin.readline().rstrip()
def coin(N, K):

    A = [int(input()) for _ in range(N)]

    count = 0
    for i in range(N-1, -1, -1):
        if K == 0:
            break
        count += K // A[i]
        K %= A[i]
    return count

N, K = map(int, input().split())
print(coin(N, K))