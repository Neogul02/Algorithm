import heapq

INF = 10**18

N = int(input())
M = int(input())

graph = [[] for _ in range(N + 1)]
dist = [INF] * (N + 1)

for _ in range(M):
    u, v, w = map(int, input().split())
    graph[u].append((v, w))

start, target = map(int, input().split())

dist[start] = 0
pq = []

heapq.heappush(pq, (0, start))  # (cost, node)

while len(pq) != 0:
    cur_cost, cur = heapq.heappop(pq)

    if cur_cost > dist[cur]:
        continue

    for nxt, w in graph[cur]:
        new_cost = cur_cost + w
        if new_cost < dist[nxt]:
            dist[nxt] = new_cost
            heapq.heappush(pq, (new_cost, nxt))

print(dist[target])