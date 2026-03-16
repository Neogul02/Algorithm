import sys
input = sys.stdin.readline

N = int(input())
h = list(map(int, input().split()))

start = h[0]
maxHeight = 0;

for i in range(1, N):    
    if(h[i-1]<h[i]): # 오르막길일때
        maxHeight = max(maxHeight, h[i]-start)
    else: # 내리막길일때
        start = h[i]        

print(maxHeight)