N = int(input())

graph = []
for i in range(N):
    row = list(map(int, input().split()))
    graph.append(row)

for k in range(N):
    for i in range(N):
        for j in range(N):
            if graph[i][k] and graph[k][j]:
                graph[i][j] = 1

for i in range(N):
    print(' '.join(map(str, graph[i])))