N = int(input())
M = int(input())

graph = [[] for _ in range(N + 1)]

for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

visited = [False] * (N + 1)

def dfs(v):
    visited[v] = True
    for nv in graph[v]:
        if not visited[nv]:
            dfs(nv)

dfs(1)
print(visited.count(True) - 1)