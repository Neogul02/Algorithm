N, W, H = map(int, input().split())
for _ in range(N):
    w = int(input())
    print('DA' if w <= max(W, H) or w*w <= W*W+H*H else 'NE')