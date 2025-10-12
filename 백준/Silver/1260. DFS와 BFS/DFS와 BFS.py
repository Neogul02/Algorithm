from collections import deque

N, M, V = map(int, input().split())
graph = [[] for _ in range(N + 1)]

for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

for adj in graph:
    adj.sort()

def dfs(v, visited):
    visited[v] = True
    print(v, end=' ')
    for u in graph[v]:
        if not visited[u]:
            dfs(u, visited)

def bfs(v):
    visited = [False] * (N + 1)
    queue = deque([v])
    visited[v] = True
    while queue:
        cur = queue.popleft()
        print(cur, end=' ')
        for u in graph[cur]:
            if not visited[u]:
                visited[u] = True
                queue.append(u)

visited = [False] * (N + 1)
dfs(V, visited)
print()
bfs(V)
