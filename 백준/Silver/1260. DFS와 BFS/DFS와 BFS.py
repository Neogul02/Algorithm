import sys
from collections import deque

input = sys.stdin.readline

N = 0
M = 0
V = 0
graph = []
visited = []

def main():
    global N, M, V, graph, visited
    
    # 입력 처리
    N, M, V = map(int, input().split())
    graph = [[] for _ in range(N + 1)]
    
    for _ in range(M):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)
    
    # 정렬
    for i in range(1, N + 1):
        graph[i].sort()
    
    # DFS 실행
    visited = [False] * (N + 1)
    dfs(V)
    print()
    
    # BFS 실행
    visited = [False] * (N + 1)
    bfs(V)
    print()


def dfs(v):
    global visited
    visited[v] = True
    print(v, end=' ')
    
    for next in graph[v]:
        if not visited[next]:
            dfs(next)

def bfs(v):
    global visited
    queue = deque()
    queue.append(v)
    visited[v] = True
    print(v, end=' ')
    
    while queue:
        curr = queue.popleft()
        
        for next in graph[curr]:
            if not visited[next]:
                visited[next] = True
                queue.append(next)
                print(next, end=' ')

main()